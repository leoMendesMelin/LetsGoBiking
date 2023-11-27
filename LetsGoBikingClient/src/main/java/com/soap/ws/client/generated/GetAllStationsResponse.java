
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
 *         &lt;element name="GetAllStationsResult" type="{http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models}ArrayOfStation" minOccurs="0"/&gt;
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
    "getAllStationsResult"
})
@XmlRootElement(name = "GetAllStationsResponse", namespace = "http://tempuri.org/")
public class GetAllStationsResponse {

    @XmlElementRef(name = "GetAllStationsResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfStation> getAllStationsResult;

    /**
     * Obtient la valeur de la propriété getAllStationsResult.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfStation }{@code >}
     *     
     */
    public JAXBElement<ArrayOfStation> getGetAllStationsResult() {
        return getAllStationsResult;
    }

    /**
     * Définit la valeur de la propriété getAllStationsResult.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfStation }{@code >}
     *     
     */
    public void setGetAllStationsResult(JAXBElement<ArrayOfStation> value) {
        this.getAllStationsResult = value;
    }

}
