
package com.soap.ws.client.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CompleteRoute complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CompleteRoute"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BikeRoute" type="{http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models}RouteResponse" minOccurs="0"/&gt;
 *         &lt;element name="WalkToEnd" type="{http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models}RouteResponse" minOccurs="0"/&gt;
 *         &lt;element name="WalkToStartStation" type="{http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models}RouteResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompleteRoute", propOrder = {
    "bikeRoute",
    "walkToEnd",
    "walkToStartStation"
})
public class CompleteRoute {

    @XmlElementRef(name = "BikeRoute", namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", type = JAXBElement.class, required = false)
    protected JAXBElement<RouteResponse> bikeRoute;
    @XmlElementRef(name = "WalkToEnd", namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", type = JAXBElement.class, required = false)
    protected JAXBElement<RouteResponse> walkToEnd;
    @XmlElementRef(name = "WalkToStartStation", namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", type = JAXBElement.class, required = false)
    protected JAXBElement<RouteResponse> walkToStartStation;

    /**
     * Obtient la valeur de la propriété bikeRoute.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RouteResponse }{@code >}
     *     
     */
    public JAXBElement<RouteResponse> getBikeRoute() {
        return bikeRoute;
    }

    /**
     * Définit la valeur de la propriété bikeRoute.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RouteResponse }{@code >}
     *     
     */
    public void setBikeRoute(JAXBElement<RouteResponse> value) {
        this.bikeRoute = value;
    }

    /**
     * Obtient la valeur de la propriété walkToEnd.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RouteResponse }{@code >}
     *     
     */
    public JAXBElement<RouteResponse> getWalkToEnd() {
        return walkToEnd;
    }

    /**
     * Définit la valeur de la propriété walkToEnd.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RouteResponse }{@code >}
     *     
     */
    public void setWalkToEnd(JAXBElement<RouteResponse> value) {
        this.walkToEnd = value;
    }

    /**
     * Obtient la valeur de la propriété walkToStartStation.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RouteResponse }{@code >}
     *     
     */
    public JAXBElement<RouteResponse> getWalkToStartStation() {
        return walkToStartStation;
    }

    /**
     * Définit la valeur de la propriété walkToStartStation.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RouteResponse }{@code >}
     *     
     */
    public void setWalkToStartStation(JAXBElement<RouteResponse> value) {
        this.walkToStartStation = value;
    }

}
