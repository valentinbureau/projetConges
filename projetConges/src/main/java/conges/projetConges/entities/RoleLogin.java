package conges.projetConges.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import conges.projetConges.controllers.rest.Views;

@Entity
@Table(name = "role_login")
@SequenceGenerator(name = "seqRoleLogin", sequenceName = "seq_role_login", initialValue = 10, allocationSize = 1)
public class RoleLogin {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRoleLogin")
	private Integer id;
	@ManyToOne
	@JsonView(Views.Common.class)
	@JoinColumn(name = "login_id", foreignKey = @ForeignKey(name = "role_login_login_id_fk"))
	private Login login;
	@Enumerated(EnumType.STRING)
	@JsonView(Views.Common.class)
	@Column(name = "role")
	private Role role;

	public RoleLogin() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleLogin other = (RoleLogin) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
