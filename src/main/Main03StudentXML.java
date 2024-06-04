package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import model.Student;
import util.MybatisConnection;

public class Main03StudentXML {
	public static void main(String[] args) {
		String namespace = "xml.student.";
		SqlSession session = MybatisConnection.getConnection();
		
		System.out.println("selectStudent=====");
		List<Student> li = session.selectList(namespace+"selectStudent");
		print(li);
		
		Student st = li.get(0);
		st.setId("kic01");
		st.setName("새로운");
		st.setStudno(9716);
		st.setJumin("111111111");
		st.setGrade(5);
		st.setMajor1(101);
		System.out.println("\ninsertStudent=====");
		int num = session.insert(namespace+"insertStudent", st);	
		System.out.println(num);
		li = session.selectList(namespace+"selectStudent");
		print(li);
		
		
		st = new Student();
		st.setGrade(2);
		st.setWeight(60);
		st.setHeight(80);
		st.setProfno(2222);
		st.setStudno(9716);
		System.out.println("\nupdateStudent=====");
		num = session.update(namespace+"updateStudent", st);
		li = session.selectList(namespace+"selectStudent");
		print(li);
		
		
		st.setStudno(9716);
		System.out.println("\ndeleteStudent=====");
		num = session.delete(namespace+"deleteStudent", st);
		li = session.selectList(namespace+"selectStudent");
		print(li);
		
		System.out.println("\nstudentRMap=====");
		li = session.selectList(namespace+"selectRMap");
		print(li);
		
		System.out.println("\nselectIf=====");
		Map map = new HashMap();
		map.put("grade", 2);
		//map.put("height", 80); 두 개는 안 됨
		li = session.selectList(namespace+"selectIf", map);
		print(li);

		
		System.out.println("\nselectChoose=====");
		map.clear();
		map.put("grade", 2);
		map.put("height", 180);
		li = session.selectList(namespace+"selectChoose", map);
		print(li);
	}
	
	public static void print(List li) {
		for(Object o : li) {
			System.out.println(o);
		}
	}
}
