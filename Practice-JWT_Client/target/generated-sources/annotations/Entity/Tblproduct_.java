package Entity;

import Entity.Tblcategory;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-02-02T21:37:37")
@StaticMetamodel(Tblproduct.class)
public class Tblproduct_ { 

    public static volatile SingularAttribute<Tblproduct, Integer> productId;
    public static volatile SingularAttribute<Tblproduct, String> productName;
    public static volatile SingularAttribute<Tblproduct, Tblcategory> categoryId;

}