package main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import mapper.ProfessorAnno;
import model.Professor;
import util.MybatisConnection;

public class Main04ProfessorAnno {
	public static void main(String[] args) {
		SqlSession session = MybatisConnection.getConnection();

		System.out.println("count====");
		int num = session.getMapper(ProfessorAnno.class).count();
		System.out.println(num);

		System.out.println("\nprofessorList====");
		List<Professor> li = session.getMapper(ProfessorAnno.class).professorList();
		print(li);
		
		// 파라미터 보내는 법, 기본형 타입
		System.out.println("\nselectdeptno====");
		li = session.getMapper(ProfessorAnno.class).selectdeptno(103);
		print(li);
		
		// 파라미터 보내는 법, 기본형 타입 X => map
		Map map = new HashMap();
		map.put("name", "명");
		map.put("position", "정교수");
		System.out.println("\nselectnameposition====");
		li = session.getMapper(ProfessorAnno.class).selectnameposition(map);
		print(li);
		
		// dynamic
		List<Integer> datas = Arrays.asList(101, 102);
		map.clear();
		map.put("position", "정교수");
		map.put("datas", datas);
		System.out.println("\nselect<script>=======");
		li = session.getMapper(ProfessorAnno.class).select(map);
		print(li);
		
		// map.claer()
		map.clear();
		System.out.println("\nselect all======");
		li = session.getMapper(ProfessorAnno.class).select(map);
		print(li);		
		
		// map.claer()
		map.clear();
		System.out.println("\nselect all======");
		li = session.getMapper(ProfessorAnno.class).select(map);
		print(li);
		
		Professor p = li.get(0);
		p.setName("새로운");
		p.setPosition("임시직");
		num = session.getMapper(ProfessorAnno.class).insertProfessor(p);
		System.out.println("\n" + num + "개 저장");
		print(li);
	}
	
	public static void print(List li) {
		for(Object o : li) {
			System.out.println(o);
		}
	}
}
