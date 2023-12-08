
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
 *         &lt;element name="EndStation" type="{http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models}Station" minOccurs="0"/&gt;
 *         &lt;element name="StartStation" type="{http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models}Station" minOccurs="0"/&gt;
 *         &lt;element name="WalkToEnd" type="{http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models}RouteResponse" minOccurs="0"/&gt;
 *         &lt;element name="WalkToStartStation" type="{http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models}RouteResponse" minOccurs="0"/&gt;
 *         &lt;element name="endPosition" type="{http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models}Position" minOccurs="0"/&gt;
 *         &lt;element name="queueId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="startPosition" type="{http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models}Position" minOccurs="0"/&gt;
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
    "endStation",
    "startStation",
    "walkToEnd",
    "walkToStartStation",
    "endPosition",
    "queueId",
    "startPosition"
})
public class CompleteRoute {

    @XmlElementRef(name = "BikeRoute", namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", type = JAXBElement.class, required = false)
    protected JAXBElement<RouteResponse> bikeRoute;
    @XmlElementRef(name = "EndStation", namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", type = JAXBElement.class, required = false)
    protected JAXBElement<Station> endStation;
    @XmlElementRef(name = "StartStation", namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", type = JAXBElement.class, required = false)
    protected JAXBElement<Station> startStation;
    @XmlElementRef(name = "WalkToEnd", namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", type = JAXBElement.class, required = false)
    protected JAXBElement<RouteResponse> walkToEnd;
    @XmlElementRef(name = "WalkToStartStation", namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", type = JAXBElement.class, required = false)
    protected JAXBElement<RouteResponse> walkToStartStation;
    @XmlElementRef(name = "endPosition", namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", type = JAXBElement.class, required = false)
    protected JAXBElement<Position> endPosition;
    @XmlElementRef(name = "queueId", namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", type = JAXBElement.class, required = false)
    protected JAXBElement<String> queueId;
    @XmlElementRef(name = "startPosition", namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", type = JAXBElement.class, required = false)
    protected JAXBElement<Position> startPosition;

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
     * Obtient la valeur de la propriété endStation.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Station }{@code >}
     *     
     */
    public JAXBElement<Station> getEndStation() {
        return endStation;
    }

    /**
     * Définit la valeur de la propriété endStation.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Station }{@code >}
     *     
     */
    public void setEndStation(JAXBElement<Station> value) {
        this.endStation = value;
    }

    /**
     * Obtient la valeur de la propriété startStation.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Station }{@code >}
     *     
     */
    public JAXBElement<Station> getStartStation() {
        return startStation;
    }

    /**
     * Définit la valeur de la propriété startStation.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Station }{@code >}
     *     
     */
    public void setStartStation(JAXBElement<Station> value) {
        this.startStation = value;
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

    /**
     * Obtient la valeur de la propriété endPosition.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Position }{@code >}
     *     
     */
    public JAXBElement<Position> getEndPosition() {
        return endPosition;
    }

    /**
     * Définit la valeur de la propriété endPosition.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Position }{@code >}
     *     
     */
    public void setEndPosition(JAXBElement<Position> value) {
        this.endPosition = value;
    }

    /**
     * Obtient la valeur de la propriété queueId.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getQueueId() {
        return queueId;
    }

    /**
     * Définit la valeur de la propriété queueId.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setQueueId(JAXBElement<String> value) {
        this.queueId = value;
    }

    /**
     * Obtient la valeur de la propriété startPosition.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Position }{@code >}
     *     
     */
    public JAXBElement<Position> getStartPosition() {
        return startPosition;
    }

    /**
     * Définit la valeur de la propriété startPosition.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Position }{@code >}
     *     
     */
    public void setStartPosition(JAXBElement<Position> value) {
        this.startPosition = value;
    }

}
