package Launch;

import Frame.Frame_Login;
import Oracle.Oracle_DAO;


public class Launch_Posc {

	public static void main(String[] args) {
		Oracle_DAO Oracle = new Oracle_DAO();
		
		if(Oracle.OracleLoading() == 0) {
			new Frame_Login();
		}
	}

}
