
package com.soap.ws.client.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="userLatitude" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="userLongitude" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "userLatitude",
    "userLongitude"
})
@XmlRootElement(name = "GetClosestContract")
public class GetClosestContract {

    protected Double userLatitude;
    protected Double userLongitude;

    /**
     * Obtient la valeur de la propriété userLatitude.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getUserLatitude() {
        return userLatitude;
    }

    /**
     * Définit la valeur de la propriété userLatitude.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setUserLatitude(Double value) {
        this.userLatitude = value;
    }

    /**
     * Obtient la valeur de la propriété userLongitude.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getUserLongitude() {
        return userLongitude;
    }

    /**
     * Définit la valeur de la propriété userLongitude.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setUserLongitude(Double value) {
        this.userLongitude = value;
    }

}
