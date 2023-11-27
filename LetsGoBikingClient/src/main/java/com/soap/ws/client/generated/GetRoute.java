
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
 *         &lt;element name="startLat" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="startLon" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="endLat" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="endLon" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="typeRoute" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "startLat",
    "startLon",
    "endLat",
    "endLon",
    "typeRoute"
})
@XmlRootElement(name = "GetRoute", namespace = "http://tempuri.org/")
public class GetRoute {

    @XmlElement(namespace = "http://tempuri.org/")
    protected Double startLat;
    @XmlElement(namespace = "http://tempuri.org/")
    protected Double startLon;
    @XmlElement(namespace = "http://tempuri.org/")
    protected Double endLat;
    @XmlElement(namespace = "http://tempuri.org/")
    protected Double endLon;
    @XmlElementRef(name = "typeRoute", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> typeRoute;

    /**
     * Obtient la valeur de la propriété startLat.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getStartLat() {
        return startLat;
    }

    /**
     * Définit la valeur de la propriété startLat.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setStartLat(Double value) {
        this.startLat = value;
    }

    /**
     * Obtient la valeur de la propriété startLon.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getStartLon() {
        return startLon;
    }

    /**
     * Définit la valeur de la propriété startLon.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setStartLon(Double value) {
        this.startLon = value;
    }

    /**
     * Obtient la valeur de la propriété endLat.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getEndLat() {
        return endLat;
    }

    /**
     * Définit la valeur de la propriété endLat.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setEndLat(Double value) {
        this.endLat = value;
    }

    /**
     * Obtient la valeur de la propriété endLon.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getEndLon() {
        return endLon;
    }

    /**
     * Définit la valeur de la propriété endLon.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setEndLon(Double value) {
        this.endLon = value;
    }

    /**
     * Obtient la valeur de la propriété typeRoute.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTypeRoute() {
        return typeRoute;
    }

    /**
     * Définit la valeur de la propriété typeRoute.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTypeRoute(JAXBElement<String> value) {
        this.typeRoute = value;
    }

}
