package org.onboard

import com.niku.union.security.SecurityIdentifier
import de.itdesign.interfaces.cmn.dto.enums.InterfaceSourceTypeEnum
import de.itdesign.interfaces.cmn.dto.enums.InterfaceTypeEnum
import de.itdesign.interfaces.cmn.service.LookupService
import de.itdesign.interfaces.io.dto.enums.FileSortingTypeEnum
import de.itdesign.interfaces.odf.dto.OdfAttribute
import de.itdesign.interfaces.odf.dto.StgToTgt
import de.itdesign.interfaces.shared.InterfaceProcessor
import de.itdesign.interfaces.shared.actuals.ActualsInterfaceProcessor
import de.itdesign.interfaces.utils.rest.CommonRestUtils
import de.itdesign.interfaces.validation.RowValidation
import groovy.sql.Sql

import java.sql.Connection

class ImportActualsFromCSV {
    Sql sql
    Object log
    ActualsInterfaceProcessor actualsInterfaceProcessor

    ImportActualsFromCSV(Connection connection, Object log, CommonRestUtils restUtils, String configInstanceCode) {
        this.sql = sql
        this.log = log
        this.actualsInterfaceProcessor = new ActualsInterfaceProcessor(connection, restUtils, log, InterfaceTypeEnum.ACTUALS, InterfaceSourceTypeEnum.CSV, configInstanceCode)

    }

    List<Integer> importSrcDataIntoStg() {
        return actualsInterfaceProcessor.importSrcDataIntoStg(FileSortingTypeEnum.FILENAME_TIMESTAMP, false)
    }

    InterfaceProcessor validateAndTransform() {
        RowValidation rowValidation = initValidations()
    }

    RowValidation initValidations() {
        SecurityIdentifier securityIdentifier = actualsInterfaceProcessor.ctx.restUtils.securityIdentifier
        Map<String, OdfAttribute> subobjectAttrApiToAttrMap = actualsInterfaceProcessor.ctx.stagingObjectService.subobjectAttrApiToAttrMap
        Map<String, StgToTgt> stgAttrApiToStgToTgtInstanceMap = actualsInterfaceProcessor.ctx.stgAttrApiToStgToTgtInstanceMap
        LookupService lookupService = new LookupService(sql)
        return RowValidation
    }
}
