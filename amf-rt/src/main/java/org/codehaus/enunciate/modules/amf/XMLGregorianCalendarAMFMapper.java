/*
 * Copyright 2006-2008 Web Cohesion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.codehaus.enunciate.modules.amf;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.DatatypeConfigurationException;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Ryan Heaton
 */
public class XMLGregorianCalendarAMFMapper implements CustomAMFMapper<XMLGregorianCalendar, Date> {

  public Date toAMF(XMLGregorianCalendar jaxbObject, AMFMappingContext context) throws AMFMappingException {
    return jaxbObject == null ? null : jaxbObject.toGregorianCalendar().getTime();
  }

  public XMLGregorianCalendar toJAXB(Date amfObject, AMFMappingContext context) throws AMFMappingException {
    if (amfObject == null) {
      return null;
    }

    try {
      GregorianCalendar gregorianCal = new GregorianCalendar();
      gregorianCal.setTime(amfObject);

      DatatypeFactory factory = DatatypeFactory.newInstance();
      return factory.newXMLGregorianCalendar(gregorianCal);
    }
    catch (DatatypeConfigurationException e) {
      throw new AMFMappingException("Internal Error mapping from AMF to an instance of XMLGregorianCalendar: ", e);
    }
  }

  public Class<? extends XMLGregorianCalendar> getJaxbClass() {
    return XMLGregorianCalendar.class;
  }

  public Class<? extends Date> getAmfClass() {
    return Date.class;
  }
}
