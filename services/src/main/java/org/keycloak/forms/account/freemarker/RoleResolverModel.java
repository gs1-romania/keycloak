package org.keycloak.forms.account.freemarker;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.keycloak.models.RoleModel;
import org.keycloak.models.UserModel;

/**
 * Pt roluri sa le gasesc in fremarker
 *
 * @date $Date: Oct 23, 2020$
 * @lastChangedBy $LastChangedBy:$
 * @author Grigoras Cristinel
 *
 */
public class RoleResolverModel {
	private UserModel user;

	public RoleResolverModel(UserModel user) {
		super();
		this.user = user;
	}

	public boolean hasRole(String roleName) {

		ArrayList<RoleModel> list = new ArrayList<>();
		list.addAll(user.getRoleMappingsStream().collect(Collectors.toList()));
		user.getGroupsStream().forEach(gm -> list.addAll(gm.getRoleMappingsStream().collect(Collectors.toList())));
		Optional<RoleModel> findFromGroup = list.stream().filter(aa -> aa.getName().equals(roleName)).findFirst();
		if (findFromGroup.isPresent()) {

			return true;
		}

		return false;
	}
}
