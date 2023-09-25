package com.dizitiveit.hrms.serviceImp;

//@Service
//public class MyUserDetailsService implements UserDetailsService{
//
//	/*
//	 * @Autowired private PasswordEncoder passwordEncoder;
//	 */
//	
//	@Autowired
//	private EmployeeDAO employeeDao;
//	
//	
//	
//	 @Override
//	 public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
//	 {
//		// Optional<Employee> employeeDetails = employeeDao.findByEmailIdOrEmpCode(userName, userName);
//		 Optional<Employee> employeeDetails = employeeDao.findByOfficialEmailIdOrPhoneNumber(userName, userName);
//		 
//		 // System.out.println(employeeDetails.toString());
//		  System.out.println("vere");
//		  employeeDetails.orElseThrow(() -> new UsernameNotFoundException("Not found: " +
//		  userName));
//		  
//		  return employeeDetails.map(MyUserDetails::new).get();
//	 }
//}
