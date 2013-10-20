package in.mum.saiyyer.testwebapp.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 
 * @author Santosh Iyer
 * 
 */
public class TestH2Connection {
	private static final String H2_DB_CLASS = "org.h2.Driver";
	private static final String H2_DB_URL = "jdbc:h2:file:~/test;IFEXISTS=true";
	private static final String H2_DB_USERNAME = "sa";
	private static final String H2_DB_PASSWORD = "sa";

	public static final class Employee {
		private final int employeeId;
		private final String employeeName;
		private final String department;
		private final int salary;

		public Employee(final int empId, final String empName, final String dept, final int salary){
			this.employeeId = empId;
			this.employeeName = empName;
			this.department = dept;
			this.salary = salary;
		}

		public int getEmployeeId() {
			return employeeId;
		}

		public String getEmployeeName() {
			return employeeName;
		}

		public String getDepartment() {
			return department;
		}

		public int getSalary() {
			return salary;
		}
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("[ ");
			sb.append("ID: "+employeeId);
			sb.append(", Name: "+employeeName);
			sb.append(", Department: "+department);
			sb.append(", Salary: "+salary);
			sb.append("]--");
			return sb.toString();
		}
	}

	public Set<Employee> getEmployees(){
		final String EMPLOYEE_SQL = "SELECT EMP_ID,EMP_NAME, DEPT_NAME, SALARY FROM EMPLOYEE E INNER JOIN DEPARTMENT D ON E.DEPT_ID = D.DEPT_ID";
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			c = getH2Connection();
			stmt = c.createStatement();
			rs = stmt.executeQuery(EMPLOYEE_SQL);
			return prepareObjects(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
				}
			}
		}
		return null;
	}
	
	private Set<Employee> prepareObjects(ResultSet rs) {
		Set<Employee> employees = new HashSet<Employee>();
		try {
			while(rs.next()){
				employees.add(new Employee(rs.getInt("EMP_ID"), rs.getString("EMP_NAME"), rs.getString("DEPT_NAME"), rs.getInt("SALARY")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	private Connection getH2Connection() {
		try {
			Class.forName(H2_DB_CLASS);
			return DriverManager.getConnection(H2_DB_URL, H2_DB_USERNAME,
					H2_DB_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("H2 DB could not be connected to..");
	}
	
	public static void main(String[] args) {
		System.out.println(new TestH2Connection().getEmployees());
	}

}
