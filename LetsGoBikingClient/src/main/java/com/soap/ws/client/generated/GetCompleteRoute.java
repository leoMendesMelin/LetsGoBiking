
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
 *         &lt;element name="startAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="endAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "startAddress",
    "endAddress"
})
@XmlRootElement(name = "GetCompleteRoute", namespace = "http://tempuri.org/")
public class GetCompleteRoute {

    @XmlElementRef(name = "startAddress", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> startAddress;
    @XmlElementRef(name = "endAddress", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> endAddress;

    /**
     * Obtient la valeur de la propriété startAddress.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStartAddress() {
        return startAddress;
    }

    /**
     * Définit la valeur de la propriété startAddress.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStartAddress(JAXBElement<String> value) {
        this.startAddress = value;
    }

    /**
     * Obtient la valeur de la propriété endAddress.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEndAddress() {
        return endAddress;
    }

    /**
     * Définit la valeur de la propriété endAddress.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEndAddress(JAXBElement<String> value) {
        this.endAddress = value;
    }

}
