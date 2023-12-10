
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
 *         &lt;element name="GetClosestStationsResult" type="{http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models}Station" minOccurs="0"/&gt;
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
    "getClosestStationsResult"
})
@XmlRootElement(name = "GetClosestStationsResponse")
public class GetClosestStationsResponse {

    @XmlElementRef(name = "GetClosestStationsResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<Station> getClosestStationsResult;

    /**
     * Obtient la valeur de la propriété getClosestStationsResult.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Station }{@code >}
     *     
     */
    public JAXBElement<Station> getGetClosestStationsResult() {
        return getClosestStationsResult;
    }

    /**
     * Définit la valeur de la propriété getClosestStationsResult.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Station }{@code >}
     *     
     */
    public void setGetClosestStationsResult(JAXBElement<Station> value) {
        this.getClosestStationsResult = value;
    }

}
