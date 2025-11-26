//package Teacher;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import tool.Action;
//
//public class StudentInformationListAction extends Action {
//
//    @Override
//    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
//
//    	int classnum = (int) req.getSession().getAttribute("classnum");
//
//        //other_edits.jsp にフォワード
//        req.getRequestDispatcher("/main/teacher/student_information_list.jsp").forward(req, res);
//    }
//}
