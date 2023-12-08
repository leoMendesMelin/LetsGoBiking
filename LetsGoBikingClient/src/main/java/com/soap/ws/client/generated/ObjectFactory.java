
package com.soap.ws.client.generated;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.soap.ws.client.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Position_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "Position");
    private final static QName _Station_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "Station");
    private final static QName _RouteResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "RouteResponse");
    private final static QName _ArrayOfFeature_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "ArrayOfFeature");
    private final static QName _Feature_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "Feature");
    private final static QName _Properties_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "Properties");
    private final static QName _ArrayOfSegment_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "ArrayOfSegment");
    private final static QName _Segment_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "Segment");
    private final static QName _ArrayOfStep_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "ArrayOfStep");
    private final static QName _Step_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "Step");
    private final static QName _Summary_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "Summary");
    private final static QName _CompleteRoute_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "CompleteRoute");
    private final static QName _ArrayOfint_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfint");
    private final static QName _AnyType_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyType");
    private final static QName _AnyURI_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyURI");
    private final static QName _Base64Binary_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "base64Binary");
    private final static QName _Boolean_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "boolean");
    private final static QName _Byte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "byte");
    private final static QName _DateTime_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "dateTime");
    private final static QName _Decimal_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "decimal");
    private final static QName _Double_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "double");
    private final static QName _Float_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "float");
    private final static QName _Int_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "int");
    private final static QName _Long_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "long");
    private final static QName _QName_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "QName");
    private final static QName _Short_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "short");
    private final static QName _String_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "string");
    private final static QName _UnsignedByte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedByte");
    private final static QName _UnsignedInt_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedInt");
    private final static QName _UnsignedLong_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedLong");
    private final static QName _UnsignedShort_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedShort");
    private final static QName _Char_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "char");
    private final static QName _Duration_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "duration");
    private final static QName _Guid_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "guid");
    private final static QName _GetPositionAddress_QNAME = new QName("http://tempuri.org/", "address");
    private final static QName _GetPositionResponseGetPositionResult_QNAME = new QName("http://tempuri.org/", "GetPositionResult");
    private final static QName _GetClosestContractResponseGetClosestContractResult_QNAME = new QName("http://tempuri.org/", "GetClosestContractResult");
    private final static QName _GetClosestStationsClosestContract_QNAME = new QName("http://tempuri.org/", "closestContract");
    private final static QName _GetClosestStationsResponseGetClosestStationsResult_QNAME = new QName("http://tempuri.org/", "GetClosestStationsResult");
    private final static QName _GetRouteTypeRoute_QNAME = new QName("http://tempuri.org/", "typeRoute");
    private final static QName _GetRouteResponseGetRouteResult_QNAME = new QName("http://tempuri.org/", "GetRouteResult");
    private final static QName _GetCompleteRouteStartAddress_QNAME = new QName("http://tempuri.org/", "startAddress");
    private final static QName _GetCompleteRouteEndAddress_QNAME = new QName("http://tempuri.org/", "endAddress");
    private final static QName _GetCompleteRouteResponseGetCompleteRouteResult_QNAME = new QName("http://tempuri.org/", "GetCompleteRouteResult");
    private final static QName _StepInstruction_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "Instruction");
    private final static QName _StepName_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "Name");
    private final static QName _StepWayPoints_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "way_points");
    private final static QName _SegmentSteps_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "Steps");
    private final static QName _PropertiesSegments_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "Segments");
    private final static QName _CompleteRouteBikeRoute_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "BikeRoute");
    private final static QName _CompleteRouteWalkToEnd_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "WalkToEnd");
    private final static QName _CompleteRouteWalkToStartStation_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "WalkToStartStation");
    private final static QName _CompleteRouteQueueId_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "queueId");
    private final static QName _RouteResponseFeatures_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "Features");
    private final static QName _StationAddress_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "address");
    private final static QName _StationContractName_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "contractName");
    private final static QName _StationName_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "name");
    private final static QName _StationPosition_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "position");
    private final static QName _StationStatus_QNAME = new QName("http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", "status");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.soap.ws.client.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPosition }
     * 
     */
    public GetPosition createGetPosition() {
        return new GetPosition();
    }

    /**
     * Create an instance of {@link GetPositionResponse }
     * 
     */
    public GetPositionResponse createGetPositionResponse() {
        return new GetPositionResponse();
    }

    /**
     * Create an instance of {@link Position }
     * 
     */
    public Position createPosition() {
        return new Position();
    }

    /**
     * Create an instance of {@link GetClosestContract }
     * 
     */
    public GetClosestContract createGetClosestContract() {
        return new GetClosestContract();
    }

    /**
     * Create an instance of {@link GetClosestContractResponse }
     * 
     */
    public GetClosestContractResponse createGetClosestContractResponse() {
        return new GetClosestContractResponse();
    }

    /**
     * Create an instance of {@link GetClosestStations }
     * 
     */
    public GetClosestStations createGetClosestStations() {
        return new GetClosestStations();
    }

    /**
     * Create an instance of {@link GetClosestStationsResponse }
     * 
     */
    public GetClosestStationsResponse createGetClosestStationsResponse() {
        return new GetClosestStationsResponse();
    }

    /**
     * Create an instance of {@link Station }
     * 
     */
    public Station createStation() {
        return new Station();
    }

    /**
     * Create an instance of {@link GetRoute }
     * 
     */
    public GetRoute createGetRoute() {
        return new GetRoute();
    }

    /**
     * Create an instance of {@link GetRouteResponse }
     * 
     */
    public GetRouteResponse createGetRouteResponse() {
        return new GetRouteResponse();
    }

    /**
     * Create an instance of {@link RouteResponse }
     * 
     */
    public RouteResponse createRouteResponse() {
        return new RouteResponse();
    }

    /**
     * Create an instance of {@link GetCompleteRoute }
     * 
     */
    public GetCompleteRoute createGetCompleteRoute() {
        return new GetCompleteRoute();
    }

    /**
     * Create an instance of {@link GetCompleteRouteResponse }
     * 
     */
    public GetCompleteRouteResponse createGetCompleteRouteResponse() {
        return new GetCompleteRouteResponse();
    }

    /**
     * Create an instance of {@link CompleteRoute }
     * 
     */
    public CompleteRoute createCompleteRoute() {
        return new CompleteRoute();
    }

    /**
     * Create an instance of {@link ArrayOfFeature }
     * 
     */
    public ArrayOfFeature createArrayOfFeature() {
        return new ArrayOfFeature();
    }

    /**
     * Create an instance of {@link Feature }
     * 
     */
    public Feature createFeature() {
        return new Feature();
    }

    /**
     * Create an instance of {@link Properties }
     * 
     */
    public Properties createProperties() {
        return new Properties();
    }

    /**
     * Create an instance of {@link ArrayOfSegment }
     * 
     */
    public ArrayOfSegment createArrayOfSegment() {
        return new ArrayOfSegment();
    }

    /**
     * Create an instance of {@link Segment }
     * 
     */
    public Segment createSegment() {
        return new Segment();
    }

    /**
     * Create an instance of {@link ArrayOfStep }
     * 
     */
    public ArrayOfStep createArrayOfStep() {
        return new ArrayOfStep();
    }

    /**
     * Create an instance of {@link Step }
     * 
     */
    public Step createStep() {
        return new Step();
    }

    /**
     * Create an instance of {@link Summary }
     * 
     */
    public Summary createSummary() {
        return new Summary();
    }

    /**
     * Create an instance of {@link ArrayOfint }
     * 
     */
    public ArrayOfint createArrayOfint() {
        return new ArrayOfint();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Position }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Position }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "Position")
    public JAXBElement<Position> createPosition(Position value) {
        return new JAXBElement<Position>(_Position_QNAME, Position.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Station }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Station }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "Station")
    public JAXBElement<Station> createStation(Station value) {
        return new JAXBElement<Station>(_Station_QNAME, Station.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RouteResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RouteResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "RouteResponse")
    public JAXBElement<RouteResponse> createRouteResponse(RouteResponse value) {
        return new JAXBElement<RouteResponse>(_RouteResponse_QNAME, RouteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFeature }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfFeature }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "ArrayOfFeature")
    public JAXBElement<ArrayOfFeature> createArrayOfFeature(ArrayOfFeature value) {
        return new JAXBElement<ArrayOfFeature>(_ArrayOfFeature_QNAME, ArrayOfFeature.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Feature }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Feature }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "Feature")
    public JAXBElement<Feature> createFeature(Feature value) {
        return new JAXBElement<Feature>(_Feature_QNAME, Feature.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Properties }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Properties }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "Properties")
    public JAXBElement<Properties> createProperties(Properties value) {
        return new JAXBElement<Properties>(_Properties_QNAME, Properties.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSegment }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfSegment }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "ArrayOfSegment")
    public JAXBElement<ArrayOfSegment> createArrayOfSegment(ArrayOfSegment value) {
        return new JAXBElement<ArrayOfSegment>(_ArrayOfSegment_QNAME, ArrayOfSegment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Segment }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Segment }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "Segment")
    public JAXBElement<Segment> createSegment(Segment value) {
        return new JAXBElement<Segment>(_Segment_QNAME, Segment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfStep }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfStep }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "ArrayOfStep")
    public JAXBElement<ArrayOfStep> createArrayOfStep(ArrayOfStep value) {
        return new JAXBElement<ArrayOfStep>(_ArrayOfStep_QNAME, ArrayOfStep.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Step }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Step }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "Step")
    public JAXBElement<Step> createStep(Step value) {
        return new JAXBElement<Step>(_Step_QNAME, Step.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Summary }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Summary }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "Summary")
    public JAXBElement<Summary> createSummary(Summary value) {
        return new JAXBElement<Summary>(_Summary_QNAME, Summary.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompleteRoute }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CompleteRoute }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "CompleteRoute")
    public JAXBElement<CompleteRoute> createCompleteRoute(CompleteRoute value) {
        return new JAXBElement<CompleteRoute>(_CompleteRoute_QNAME, CompleteRoute.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/Arrays", name = "ArrayOfint")
    public JAXBElement<ArrayOfint> createArrayOfint(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_ArrayOfint_QNAME, ArrayOfint.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Object }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyType")
    public JAXBElement<Object> createAnyType(Object value) {
        return new JAXBElement<Object>(_AnyType_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyURI")
    public JAXBElement<String> createAnyURI(String value) {
        return new JAXBElement<String>(_AnyURI_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "base64Binary")
    public JAXBElement<byte[]> createBase64Binary(byte[] value) {
        return new JAXBElement<byte[]>(_Base64Binary_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "boolean")
    public JAXBElement<Boolean> createBoolean(Boolean value) {
        return new JAXBElement<Boolean>(_Boolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "byte")
    public JAXBElement<Byte> createByte(Byte value) {
        return new JAXBElement<Byte>(_Byte_QNAME, Byte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "dateTime")
    public JAXBElement<XMLGregorianCalendar> createDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DateTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "decimal")
    public JAXBElement<BigDecimal> createDecimal(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Decimal_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Double }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "double")
    public JAXBElement<Double> createDouble(Double value) {
        return new JAXBElement<Double>(_Double_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Float }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "float")
    public JAXBElement<Float> createFloat(Float value) {
        return new JAXBElement<Float>(_Float_QNAME, Float.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "int")
    public JAXBElement<Integer> createInt(Integer value) {
        return new JAXBElement<Integer>(_Int_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Long }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "long")
    public JAXBElement<Long> createLong(Long value) {
        return new JAXBElement<Long>(_Long_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QName }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link QName }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "QName")
    public JAXBElement<QName> createQName(QName value) {
        return new JAXBElement<QName>(_QName_QNAME, QName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Short }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "short")
    public JAXBElement<Short> createShort(Short value) {
        return new JAXBElement<Short>(_Short_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Short }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedByte")
    public JAXBElement<Short> createUnsignedByte(Short value) {
        return new JAXBElement<Short>(_UnsignedByte_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Long }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedInt")
    public JAXBElement<Long> createUnsignedInt(Long value) {
        return new JAXBElement<Long>(_UnsignedInt_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedLong")
    public JAXBElement<BigInteger> createUnsignedLong(BigInteger value) {
        return new JAXBElement<BigInteger>(_UnsignedLong_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedShort")
    public JAXBElement<Integer> createUnsignedShort(Integer value) {
        return new JAXBElement<Integer>(_UnsignedShort_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "char")
    public JAXBElement<Integer> createChar(Integer value) {
        return new JAXBElement<Integer>(_Char_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Duration }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Duration }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "duration")
    public JAXBElement<Duration> createDuration(Duration value) {
        return new JAXBElement<Duration>(_Duration_QNAME, Duration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "guid")
    public JAXBElement<String> createGuid(String value) {
        return new JAXBElement<String>(_Guid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "address", scope = GetPosition.class)
    public JAXBElement<String> createGetPositionAddress(String value) {
        return new JAXBElement<String>(_GetPositionAddress_QNAME, String.class, GetPosition.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Position }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Position }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetPositionResult", scope = GetPositionResponse.class)
    public JAXBElement<Position> createGetPositionResponseGetPositionResult(Position value) {
        return new JAXBElement<Position>(_GetPositionResponseGetPositionResult_QNAME, Position.class, GetPositionResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetClosestContractResult", scope = GetClosestContractResponse.class)
    public JAXBElement<String> createGetClosestContractResponseGetClosestContractResult(String value) {
        return new JAXBElement<String>(_GetClosestContractResponseGetClosestContractResult_QNAME, String.class, GetClosestContractResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "closestContract", scope = GetClosestStations.class)
    public JAXBElement<String> createGetClosestStationsClosestContract(String value) {
        return new JAXBElement<String>(_GetClosestStationsClosestContract_QNAME, String.class, GetClosestStations.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Station }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Station }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetClosestStationsResult", scope = GetClosestStationsResponse.class)
    public JAXBElement<Station> createGetClosestStationsResponseGetClosestStationsResult(Station value) {
        return new JAXBElement<Station>(_GetClosestStationsResponseGetClosestStationsResult_QNAME, Station.class, GetClosestStationsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "typeRoute", scope = GetRoute.class)
    public JAXBElement<String> createGetRouteTypeRoute(String value) {
        return new JAXBElement<String>(_GetRouteTypeRoute_QNAME, String.class, GetRoute.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RouteResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RouteResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetRouteResult", scope = GetRouteResponse.class)
    public JAXBElement<RouteResponse> createGetRouteResponseGetRouteResult(RouteResponse value) {
        return new JAXBElement<RouteResponse>(_GetRouteResponseGetRouteResult_QNAME, RouteResponse.class, GetRouteResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "startAddress", scope = GetCompleteRoute.class)
    public JAXBElement<String> createGetCompleteRouteStartAddress(String value) {
        return new JAXBElement<String>(_GetCompleteRouteStartAddress_QNAME, String.class, GetCompleteRoute.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "endAddress", scope = GetCompleteRoute.class)
    public JAXBElement<String> createGetCompleteRouteEndAddress(String value) {
        return new JAXBElement<String>(_GetCompleteRouteEndAddress_QNAME, String.class, GetCompleteRoute.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompleteRoute }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CompleteRoute }{@code >}
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetCompleteRouteResult", scope = GetCompleteRouteResponse.class)
    public JAXBElement<CompleteRoute> createGetCompleteRouteResponseGetCompleteRouteResult(CompleteRoute value) {
        return new JAXBElement<CompleteRoute>(_GetCompleteRouteResponseGetCompleteRouteResult_QNAME, CompleteRoute.class, GetCompleteRouteResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "Instruction", scope = Step.class)
    public JAXBElement<String> createStepInstruction(String value) {
        return new JAXBElement<String>(_StepInstruction_QNAME, String.class, Step.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "Name", scope = Step.class)
    public JAXBElement<String> createStepName(String value) {
        return new JAXBElement<String>(_StepName_QNAME, String.class, Step.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "way_points", scope = Step.class)
    public JAXBElement<ArrayOfint> createStepWayPoints(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_StepWayPoints_QNAME, ArrayOfint.class, Step.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfStep }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfStep }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "Steps", scope = Segment.class)
    public JAXBElement<ArrayOfStep> createSegmentSteps(ArrayOfStep value) {
        return new JAXBElement<ArrayOfStep>(_SegmentSteps_QNAME, ArrayOfStep.class, Segment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSegment }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfSegment }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "Segments", scope = Properties.class)
    public JAXBElement<ArrayOfSegment> createPropertiesSegments(ArrayOfSegment value) {
        return new JAXBElement<ArrayOfSegment>(_PropertiesSegments_QNAME, ArrayOfSegment.class, Properties.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Summary }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Summary }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "Summary", scope = Properties.class)
    public JAXBElement<Summary> createPropertiesSummary(Summary value) {
        return new JAXBElement<Summary>(_Summary_QNAME, Summary.class, Properties.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Properties }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Properties }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "Properties", scope = Feature.class)
    public JAXBElement<Properties> createFeatureProperties(Properties value) {
        return new JAXBElement<Properties>(_Properties_QNAME, Properties.class, Feature.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RouteResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RouteResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "BikeRoute", scope = CompleteRoute.class)
    public JAXBElement<RouteResponse> createCompleteRouteBikeRoute(RouteResponse value) {
        return new JAXBElement<RouteResponse>(_CompleteRouteBikeRoute_QNAME, RouteResponse.class, CompleteRoute.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RouteResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RouteResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "WalkToEnd", scope = CompleteRoute.class)
    public JAXBElement<RouteResponse> createCompleteRouteWalkToEnd(RouteResponse value) {
        return new JAXBElement<RouteResponse>(_CompleteRouteWalkToEnd_QNAME, RouteResponse.class, CompleteRoute.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RouteResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RouteResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "WalkToStartStation", scope = CompleteRoute.class)
    public JAXBElement<RouteResponse> createCompleteRouteWalkToStartStation(RouteResponse value) {
        return new JAXBElement<RouteResponse>(_CompleteRouteWalkToStartStation_QNAME, RouteResponse.class, CompleteRoute.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "queueId", scope = CompleteRoute.class)
    public JAXBElement<String> createCompleteRouteQueueId(String value) {
        return new JAXBElement<String>(_CompleteRouteQueueId_QNAME, String.class, CompleteRoute.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFeature }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ArrayOfFeature }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "Features", scope = RouteResponse.class)
    public JAXBElement<ArrayOfFeature> createRouteResponseFeatures(ArrayOfFeature value) {
        return new JAXBElement<ArrayOfFeature>(_RouteResponseFeatures_QNAME, ArrayOfFeature.class, RouteResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "address", scope = Station.class)
    public JAXBElement<String> createStationAddress(String value) {
        return new JAXBElement<String>(_StationAddress_QNAME, String.class, Station.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "contractName", scope = Station.class)
    public JAXBElement<String> createStationContractName(String value) {
        return new JAXBElement<String>(_StationContractName_QNAME, String.class, Station.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "name", scope = Station.class)
    public JAXBElement<String> createStationName(String value) {
        return new JAXBElement<String>(_StationName_QNAME, String.class, Station.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Position }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Position }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "position", scope = Station.class)
    public JAXBElement<Position> createStationPosition(Position value) {
        return new JAXBElement<Position>(_StationPosition_QNAME, Position.class, Station.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/LetsGoBikingLibrary2.Models", name = "status", scope = Station.class)
    public JAXBElement<String> createStationStatus(String value) {
        return new JAXBElement<String>(_StationStatus_QNAME, String.class, Station.class, value);
    }

}
