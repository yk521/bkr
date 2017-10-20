package bkr.core.user.predicateimpl;

import com.querydsl.core.types.Predicate;

import bkr.core.user.entity.QUser;

public final class UserPredicate{

	private UserPredicate(){}
	
	public static Predicate nameAndPassword(String name, String password){
		return QUser.user.name.eq(name).and(QUser.user.password.eq(password));
	}
	
}
