
package com.soap.ws.client.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="contract" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="station" type="{http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models}Station" minOccurs="0"/&gt;
 *         &lt;element name="typeStation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "contract",
    "station",
    "typeStation"
})
@XmlRootElement(name = "CheckStationAvailable")
public class CheckStationAvailable {

    @XmlElementRef(name = "contract", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> contract;
    @XmlElementRef(name = "station", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<Station> station;
    @XmlElementRef(name = "typeStation", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> typeStation;

    /**
     * Obtient la valeur de la propriété contract.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getContract() {
        return contract;
    }

    /**
     * Définit la valeur de la propriété contract.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setContract(JAXBElement<String> value) {
        this.contract = value;
    }

    /**
     * Obtient la valeur de la propriété station.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Station }{@code >}
     *     
     */
    public JAXBElement<Station> getStation() {
        return station;
    }

    /**
     * Définit la valeur de la propriété station.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Station }{@code >}
     *     
     */
    public void setStation(JAXBElement<Station> value) {
        this.station = value;
    }

    /**
     * Obtient la valeur de la propriété typeStation.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTypeStation() {
        return typeStation;
    }

    /**
     * Définit la valeur de la propriété typeStation.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTypeStation(JAXBElement<String> value) {
        this.typeStation = value;
    }

}
