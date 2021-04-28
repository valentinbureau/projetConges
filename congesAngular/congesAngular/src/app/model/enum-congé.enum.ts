export enum EnumCongé {
conges_payes=1,
conges_sans_solde=2,
absence_autorisee=3,
absence_justifiee=4,
}

export const TypeCongeMapping = {
[EnumCongé.conges_payes]:"Congés payés",
[EnumCongé.conges_sans_solde]:"Congés sans solde",
[EnumCongé.absence_autorisee]:"Absensce autorisée",
[EnumCongé.absence_justifiee]:"Absence justifiée",

}
