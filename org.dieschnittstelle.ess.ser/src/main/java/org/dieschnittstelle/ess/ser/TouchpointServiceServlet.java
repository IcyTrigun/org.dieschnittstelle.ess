package org.dieschnittstelle.ess.ser;

import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.ess.entities.crm.AbstractTouchpoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.dieschnittstelle.ess.utils.Utils.show;

public class TouchpointServiceServlet extends HttpServlet {

	protected static Logger logger = org.apache.logging.log4j.LogManager
			.getLogger(TouchpointServiceServlet.class);

	public TouchpointServiceServlet() {
		show("TouchpointServiceServlet: constructor invoked\n");
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("doGet()");

		// we assume here that GET will only be used to return the list of all
		// touchpoints

		// obtain the executor for reading out the touchpoints
		TouchpointCRUDExecutor exec = (TouchpointCRUDExecutor) getServletContext()
				.getAttribute("touchpointCRUD");
		try {
			// set the status
			response.setStatus(HttpServletResponse.SC_OK);
			// obtain the output stream from the response and write the list of
			// touchpoints into the stream
			ObjectOutputStream oos = new ObjectOutputStream(
					response.getOutputStream());
			// write the object
			oos.writeObject(exec.readAllTouchpoints());
			oos.close();
		} catch (Exception e) {
			String err = "got exception: " + e;
			logger.error(err, e);
			throw new RuntimeException(e);
		}

	}

	/*
	 * TODO: SER3 server-side implementation of createNewTouchpoint
	 */

	@Override	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		show("TouchpointServiceServlet: doPost() invoked\n");
		// assume POST will only be used for touchpoint creation, i.e. there is
		// no need to check the uri that has been used

		// obtain the executor for reading out the touchpoints from the servlet context using the touchpointCRUD attribute
		TouchpointCRUDExecutor exec = (TouchpointCRUDExecutor) getServletContext().getAttribute("touchpointCRUD");
		try {
			// create an ObjectInputStream from the request's input stream
			ObjectInputStream ois = new ObjectInputStream(request.getInputStream()); // an den Body ran
			// read an AbstractTouchpoint object from the stream
			AbstractTouchpoint tp = (AbstractTouchpoint) ois.readObject();
			// call the create method on the executor and take its return value

			exec.createTouchpoint(tp);
			// set the response status as successful, using the appropriate
			// constant from HttpServletResponse
			response.setStatus(HttpServletResponse.SC_CREATED);;

			// then write the object to the response's output stream, using a
			// wrapping ObjectOutputStream
			ObjectOutputStream oos = new ObjectOutputStream(response.getOutputStream()); // kann auf res body zugreifen
			// ... and write the object to the stream

			oos.writeObject(tp);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

		try {
			//get id an delete url point
			String uri =  req.getPathInfo();
			String idStr = uri.replaceAll("[^0-9]", "");
			long id = Long.parseLong(idStr);

			//get Touchpoint
			TouchpointCRUDExecutor exec = (TouchpointCRUDExecutor) getServletContext()
					.getAttribute("touchpointCRUD");
			//delete -> true if sucessful
			boolean success = exec.deleteTouchpoint(id);
			if(success){
				response.setStatus(HttpServletResponse.SC_OK);
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}


		} catch (Exception e) {
			String err = "got exception while delete: " + e;
			logger.error(err, e);
			throw new RuntimeException(e);
		}
	}

}
