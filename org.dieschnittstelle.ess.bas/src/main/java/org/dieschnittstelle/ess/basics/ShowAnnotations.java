package org.dieschnittstelle.ess.basics;


import org.dieschnittstelle.ess.basics.annotations.AnnotatedStockItemBuilder;
import org.dieschnittstelle.ess.basics.annotations.DisplayAs;
import org.dieschnittstelle.ess.basics.annotations.StockItemProxyImpl;
import org.dieschnittstelle.ess.basics.annotations.stockitemtypes.Schokolade;

import java.lang.reflect.Field;
import java.util.Set;

import static org.dieschnittstelle.ess.utils.Utils.*;

public class ShowAnnotations  {

	public static void main(String[] args) throws IllegalAccessException {
		// we initialise the collection
		StockItemCollection collection = new StockItemCollection(
				"stockitems_annotations.xml", new AnnotatedStockItemBuilder());
		// we load the contents into the collection
		collection.load();

		for (IStockItem consumable : collection.getStockItems()) {
			showAttributes(((StockItemProxyImpl)consumable).getProxiedObject());
		}

		// we initialise a consumer
		Consumer consumer = new Consumer();
		// ... and let them consume
		consumer.doShopping(collection.getStockItems());
	/*	for (IStockItem consumable : collection.getStockItems()) {
			showAttributes(consumable);
		}*/
	}

	/*
	 * TODO BAS2
	 */
	private static void showAttributes(Object consumable) throws IllegalAccessException {
		/*
		Lösung für Bas2
		show("class is: " + consumable.getClass());
		StringBuilder attributeString= new StringBuilder();
		Class klass = consumable.getClass();
		attributeString.append(klass.getSimpleName()).append(" ");
		for (Field field : consumable.getClass().getDeclaredFields()) {
			attributeString.append(field.getName());
			attributeString.append(":  ");
			field.setAccessible(true);
			attributeString.append(field.get(consumable));
			attributeString.append("  ");
		}
		System.out.println(attributeString); */


		show("class is: " + consumable.getClass());
		StringBuilder attributeString= new StringBuilder();
		Class klass = consumable.getClass();
		attributeString.append(klass.getSimpleName()).append(" ");
		for (Field field : consumable.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			if(field.getAnnotation(DisplayAs.class)!=null){
				attributeString.append(field.getAnnotation(DisplayAs.class).value());
			}else{
				attributeString.append(field.getName());
			}
			attributeString.append(":  ");

			attributeString.append(field.get(consumable));
			attributeString.append("  ");
		}
		System.out.println(attributeString);
	}
	// TODO BAS2: create a string representation of consumable by iterating
	//  over the object's attributes / fields as provided by its class
	//  and reading out the attribute values. The string representation
	//  will then be built from the field names and field values.
	//  Note that only read-access to fields via getters or direct access
	//  is required here.

	// TODO BAS3: if the new @DisplayAs annotation is present on a field,
	//  the string representation will not use the field's name, but the name
	//  specified in the the annotation. Regardless of @DisplayAs being present
	//  or not, the field's value will be included in the string representation.
}


