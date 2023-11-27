
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
 *         &lt;element name="GetPositionResult" type="{http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models}Position" minOccurs="0"/&gt;
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
    "getPositionResult"
})
@XmlRootElement(name = "GetPositionResponse", namespace = "http://tempuri.org/")
public class GetPositionResponse {

    @XmlElementRef(name = "GetPositionResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<Position> getPositionResult;

    /**
     * Obtient la valeur de la propriété getPositionResult.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Position }{@code >}
     *     
     */
    public JAXBElement<Position> getGetPositionResult() {
        return getPositionResult;
    }

    /**
     * Définit la valeur de la propriété getPositionResult.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Position }{@code >}
     *     
     */
    public void setGetPositionResult(JAXBElement<Position> value) {
        this.getPositionResult = value;
    }

}
