package formation.sopra.formationSrpingBoot.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ModuleFormationPK implements Serializable {
	@ManyToOne
	@JoinColumn(name = "id_formation", foreignKey = @ForeignKey(name = "module_formation_id_formation_fk"))
	private Formation formation;
	@ManyToOne
	@JoinColumn(name = "id_module", foreignKey = @ForeignKey(name = "module_formation_id_module_fk"))
	private Module module;

	public ModuleFormationPK() {

	}

	public ModuleFormationPK(Formation formation, Module module) {
		super();
		this.formation = formation;
		this.module = module;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((formation == null) ? 0 : formation.hashCode());
		result = prime * result + ((module == null) ? 0 : module.hashCode());
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
		ModuleFormationPK other = (ModuleFormationPK) obj;
		if (formation == null) {
			if (other.formation != null)
				return false;
		} else if (!formation.equals(other.formation))
			return false;
		if (module == null) {
			if (other.module != null)
				return false;
		} else if (!module.equals(other.module))
			return false;
		return true;
	}

}
