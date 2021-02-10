package zombie.deliziusz.miclientecpcontactoskarla;

import android.net.Uri;

public class ContactosContractProvider {
    public static final Uri CONTENT_URI_CONTACTOS =
            Uri.parse( "content://zombie.deliziusz.mibasedatoskarla/contactos");

    public static final  String [] PROJECTION_CONTACTOS
            = {
            "_id", "usuario", "email", "tel"
    };

    public static final  String FIELD_ID = "_id";
    public static final  String FIELD_USUARIO = "usuario";
    public static final  String FIELD_EMAIL = "email";
}
