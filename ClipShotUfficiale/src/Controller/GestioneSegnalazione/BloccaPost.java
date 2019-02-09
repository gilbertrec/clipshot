package Controller.GestioneSegnalazione;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Manager.PostDAO;
import Manager.SegnalazioneDAO;
import Model.PostBean;
import Model.SegnalazioneBean;

@WebServlet("/BloccaPost")
public class BloccaPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BloccaPost() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ssn = request.getSession();
		if(ssn != null) {
			String username = (String) ssn.getAttribute("username");
			if(username != null) {
				SegnalazioneDAO segnalazioneDAO = new SegnalazioneDAO();
				SegnalazioneBean segnalazioneBean = new SegnalazioneBean();
				int idSegnalazione = Integer.parseInt(request.getParameter("segnalazioneInfo"));
				String idUtente = (String) request.getParameter("idUtenteInfo");
				try {
					segnalazioneBean = segnalazioneDAO.doRetrieveByKey(idSegnalazione, idUtente);
				} catch (Exception e) { // segnalazione not exist
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				PostDAO postDAO = new PostDAO();
				PostBean postBean = new PostBean();
				String idPostString = String.valueOf(segnalazioneBean.getIdPost());
				try {
					int idPost = Integer.parseInt(idPostString);
					postBean = postDAO.doRetrieveByKey(idPost, segnalazioneBean.getIdUtentePost());
				} catch (SQLException e1) { //post segnalato - not exist
					e1.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				postBean.setStato("BLOCKED");
				try {
					postDAO.doSaveOrUpdate(postBean);
				} catch (SQLException e1) { //update post fallito
					e1.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
				segnalazioneBean.setStato("completata");
				try {
					segnalazioneDAO.doSaveOrUpdate(segnalazioneBean);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				} catch (Exception e) { //update segnalazione fallito
					e.printStackTrace();
					RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
					requestDispatcher.forward(request, response);
				}
			} else { // username == null
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
				requestDispatcher.forward(request, response);
			}		
		} else { // ssn == null
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(""); 
			requestDispatcher.forward(request, response);
		}
	}
}
