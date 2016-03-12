package pozzo.apps.cheesecake.model;

import com.activeandroid.Model;

import java.lang.reflect.Field;

import pozzo.apps.cheesecake.util.Log;

/**
 * Model generalization, so we can add our own functionalities.
 *
 * @author Luiz Gustavo Pozzo
 * @since 12/03/16
 */
public class BaseModel extends Model {
	/**
	 * Bypass para setarmos o ID no ActiveAndroid.
	 */
	public void setId(Long mId) {
		try {
			Class clas = getClass().getSuperclass().getSuperclass();
			while(!clas.getName().equals(Model.class.getName()))
				clas = clas.getSuperclass();

			Field mIdField = clas.getDeclaredField("mId");
			mIdField.setAccessible(true);
			mIdField.set(this, mId);
		} catch (NoSuchFieldException e) {
			//Estas duas excessoes nao devem ocorrer, jamais!
			Log.e(e);
		} catch (IllegalAccessException e) {
			Log.e(e);
		}
	}
}
