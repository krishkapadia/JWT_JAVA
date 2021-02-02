package Entity;

import Entity.Tblproduct;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-02-02T21:38:18")
@StaticMetamodel(Tblcategory.class)
public class Tblcategory_ { 

    public static volatile ListAttribute<Tblcategory, Tblproduct> tblproductList;
    public static volatile SingularAttribute<Tblcategory, String> categoryName;
    public static volatile SingularAttribute<Tblcategory, Integer> categoryId;

}