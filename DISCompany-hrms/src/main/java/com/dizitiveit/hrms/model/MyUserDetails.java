package com.dizitiveit.hrms.model;

//
//public class MyUserDetails implements UserDetails {
//
//	
//	private static final long serialVersionUID = 1L;
//	private String userName;
//	
//	private boolean active;
//	private List<GrantedAuthority> authorities;
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return userName;
//	}
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//	
//	
//	public MyUserDetails(Employee employee) {
//		this.userName = employee.getOfficialEmailId();
//		this.active = employee.isStatus();
//		
//		this.authorities = Arrays.stream(employee.getRoles().getRoleName().split(",")).map(SimpleGrantedAuthority::new)
//				.collect(Collectors.toList());
//	}
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	
//	
//}
