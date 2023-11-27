
package com.soap.ws.client.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
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
 *         &lt;element name="numberOfStations" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="closestContract" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "userLongitude",
    "numberOfStations",
    "closestContract"
})
@XmlRootElement(name = "GetClosestStations", namespace = "http://tempuri.org/")
public class GetClosestStations {

    @XmlElement(namespace = "http://tempuri.org/")
    protected Double userLatitude;
    @XmlElement(namespace = "http://tempuri.org/")
    protected Double userLongitude;
    @XmlElement(namespace = "http://tempuri.org/")
    protected Integer numberOfStations;
    @XmlElementRef(name = "closestContract", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> closestContract;

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

    /**
     * Obtient la valeur de la propriété numberOfStations.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfStations() {
        return numberOfStations;
    }

    /**
     * Définit la valeur de la propriété numberOfStations.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberOfStations(Integer value) {
        this.numberOfStations = value;
    }

    /**
     * Obtient la valeur de la propriété closestContract.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getClosestContract() {
        return closestContract;
    }

    /**
     * Définit la valeur de la propriété closestContract.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setClosestContract(JAXBElement<String> value) {
        this.closestContract = value;
    }

}
