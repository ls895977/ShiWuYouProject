package com.example.shiwuyouproject.net.bean

data class User(
    val departs: List<Any>,
    val multi_depart: Int,
    val sysAllDictItems: SysAllDictItems,
    val token: String,
    val userInfo: UserInfo
)

data class SysAllDictItems(
    val activiti_sync: List<ActivitiSync>,
    val assay_receive_result: List<AssayReceiveResult>,
    val assay_type: List<AssayType>,
    val audit_modules: List<AuditModule>,
    val bpm_process_type: List<BpmProcessType>,
    val bpm_status: List<BpmStatu>,
    val ceshi_online: List<CeshiOnline>,
    val cgform_table_type: List<CgformTableType>,
    val chemical_unit: List<ChemicalUnit>,
    val code_type: List<CodeType>,
    val cycle_type: List<CycleType>,
    val data_type: List<DataType>,
    val database_type: List<DatabaseType>,
    val days_options: List<DaysOption>,
    val del_flag: List<DelFlag>,
    val depart_status: List<DepartStatu>,
    val device_param_type: List<DeviceParamType>,
    val dict_item_status: List<DictItemStatu>,
    val due_method: List<DueMethod>,
    val due_state: List<DueState>,
    val enable_disable: List<EnableDisable>,
    val eoa_cms_menu_type: List<EoaCmsMenuType>,
    val eoa_plan_status: List<EoaPlanStatu>,
    val eoa_plan_type: List<EoaPlanType>,
    val form_perms_type: List<FormPermsType>,
    val global_perms_type: List<GlobalPermsType>,
    val history_type: List<HistoryType>,
    val inspeciton_point_type: List<InspecitonPointType>,
    val inspect_proj_type: List<InspectProjType>,
    val inspection_path_type: List<InspectionPathType>,
    val locate_types: List<LocateType>,
    val log_type: List<LogType>,
    val measurement_unit: List<MeasurementUnit>,
    val menu_type: List<MenuType>,
    val month_every_days: List<MonthEveryDay>,
    val msgSendStatus: List<MsgSendStatu>,
    val msgType: List<MsgType>,
    val msg_category: List<MsgCategory>,
    val msg_type: List<MsgTypeX>,
    val oc: List<Oc>,
    val ol_form_biz_type: List<OlFormBizType>,
    val online_graph_data_type: List<OnlineGraphDataType>,
    val online_graph_display_template: List<OnlineGraphDisplayTemplate>,
    val online_graph_type: List<OnlineGraphType>,
    val operate_type: List<OperateType>,
    val org_category: List<OrgCategory>,
    val perms_type: List<PermsType>,
    val plant_area_type: List<PlantAreaType>,
    val plant_area_type_code: List<PlantAreaTypeCode>,
    val plant_area_type_type: List<PlantAreaTypeType>,
    val point_record_type: List<PointRecordType>,
    val position_rank: List<PositionRank>,
    val priority: List<Priority>,
    val prop_state_type: List<PropStateType>,
    val quartz_status: List<QuartzStatu>,
    val register_type: List<RegisterType>,
    val repair_trouble_state: List<RepairTroubleState>,
    val rule_conditions: List<RuleCondition>,
    val sample_plan_period_type: List<SamplePlanPeriodType>,
    val sample_state: List<SampleState>,
    val sample_test_audit_status: List<SampleTestAuditStatu>,
    val sample_test_process: List<SampleTestProces>,
    val sample_test_type: List<SampleTestType>,
    val send_status: List<SendStatu>,
    val sex: List<Sex>,
    val status: List<Statu>,
    val stock_order_state: List<StockOrderState>,
    val stock_order_type: List<StockOrderType>,
    val test_item_data_type: List<TestItemDataType>,
    val test_item_unit: List<TestItemUnit>,
    val trouble_ticket_state: List<TroubleTicketState>,
    val urgent_level: List<UrgentLevel>,
    val user_status: List<UserStatu>,
    val user_type: List<UserType>,
    val valid_status: List<ValidStatu>,
    val value_limit_type: List<ValueLimitType>,
    val warn_type: List<WarnType>,
    val water_config_code: List<WaterConfigCode>,
    val week_options: List<WeekOption>,
    val yn: List<Yn>
)

data class UserInfo(
    val activitiSync: Int,
    val avatar: String,
    val birthday: String,
    val createBy: Any,
    val createTime: String,
    val delFlag: Int,
    val departIds: String,
    val email: String,
    val id: String,
    val orgCode: String,
    val phone: String,
    val post: String,
    val realname: String,
    val sex: Int,
    val status: Int,
    val telephone: Any,
    val thirdId: Any,
    val thirdType: Any,
    val updateBy: String,
    val updateTime: String,
    val userIdentity: Int,
    val username: String,
    val workNo: String,
    var pwd:String
)

data class ActivitiSync(
    val text: String,
    val title: String,
    val value: String
)

data class AssayReceiveResult(
    val text: String,
    val title: String,
    val value: String
)

data class AssayType(
    val text: String,
    val title: String,
    val value: String
)

data class AuditModule(
    val text: String,
    val title: String,
    val value: String
)

data class BpmProcessType(
    val text: String,
    val title: String,
    val value: String
)

data class BpmStatu(
    val text: String,
    val title: String,
    val value: String
)

data class CeshiOnline(
    val text: String,
    val title: String,
    val value: String
)

data class CgformTableType(
    val text: String,
    val title: String,
    val value: String
)

data class ChemicalUnit(
    val text: String,
    val title: String,
    val value: String
)

data class CodeType(
    val text: String,
    val title: String,
    val value: String
)

data class CycleType(
    val text: String,
    val title: String,
    val value: String
)

data class DataType(
    val text: String,
    val title: String,
    val value: String
)

data class DatabaseType(
    val text: String,
    val title: String,
    val value: String
)

data class DaysOption(
    val text: String,
    val title: String,
    val value: String
)

data class DelFlag(
    val text: String,
    val title: String,
    val value: String
)

data class DepartStatu(
    val text: String,
    val title: String,
    val value: String
)

data class DeviceParamType(
    val text: String,
    val title: String,
    val value: String
)

data class DictItemStatu(
    val text: String,
    val title: String,
    val value: String
)

data class DueMethod(
    val text: String,
    val title: String,
    val value: String
)

data class DueState(
    val text: String,
    val title: String,
    val value: String
)

data class EnableDisable(
    val text: String,
    val title: String,
    val value: String
)

data class EoaCmsMenuType(
    val text: String,
    val title: String,
    val value: String
)

data class EoaPlanStatu(
    val text: String,
    val title: String,
    val value: String
)

data class EoaPlanType(
    val text: String,
    val title: String,
    val value: String
)

data class FormPermsType(
    val text: String,
    val title: String,
    val value: String
)

data class GlobalPermsType(
    val text: String,
    val title: String,
    val value: String
)

data class HistoryType(
    val text: String,
    val title: String,
    val value: String
)

data class InspecitonPointType(
    val text: String,
    val title: String,
    val value: String
)

data class InspectProjType(
    val text: String,
    val title: String,
    val value: String
)

data class InspectionPathType(
    val text: String,
    val title: String,
    val value: String
)

data class LocateType(
    val text: String,
    val title: String,
    val value: String
)

data class LogType(
    val text: String,
    val title: String,
    val value: String
)

data class MeasurementUnit(
    val text: String,
    val title: String,
    val value: String
)

data class MenuType(
    val text: String,
    val title: String,
    val value: String
)

data class MonthEveryDay(
    val text: String,
    val title: String,
    val value: String
)

data class MsgSendStatu(
    val text: String,
    val title: String,
    val value: String
)

data class MsgType(
    val text: String,
    val title: String,
    val value: String
)

data class MsgCategory(
    val text: String,
    val title: String,
    val value: String
)

data class MsgTypeX(
    val text: String,
    val title: String,
    val value: String
)

data class Oc(
    val text: String,
    val title: String,
    val value: String
)

data class OlFormBizType(
    val text: String,
    val title: String,
    val value: String
)

data class OnlineGraphDataType(
    val text: String,
    val title: String,
    val value: String
)

data class OnlineGraphDisplayTemplate(
    val text: String,
    val title: String,
    val value: String
)

data class OnlineGraphType(
    val text: String,
    val title: String,
    val value: String
)

data class OperateType(
    val text: String,
    val title: String,
    val value: String
)

data class OrgCategory(
    val text: String,
    val title: String,
    val value: String
)

data class PermsType(
    val text: String,
    val title: String,
    val value: String
)

data class PlantAreaType(
    val text: String,
    val title: String,
    val value: String
)

data class PlantAreaTypeCode(
    val text: String,
    val title: String,
    val value: String
)

data class PlantAreaTypeType(
    val text: String,
    val title: String,
    val value: String
)

data class PointRecordType(
    val text: String,
    val title: String,
    val value: String
)

data class PositionRank(
    val text: String,
    val title: String,
    val value: String
)

data class Priority(
    val text: String,
    val title: String,
    val value: String
)

data class PropStateType(
    val text: String,
    val title: String,
    val value: String
)

data class QuartzStatu(
    val text: String,
    val title: String,
    val value: String
)

data class RegisterType(
    val text: String,
    val title: String,
    val value: String
)

data class RepairTroubleState(
    val text: String,
    val title: String,
    val value: String
)

data class RuleCondition(
    val text: String,
    val title: String,
    val value: String
)

data class SamplePlanPeriodType(
    val text: String,
    val title: String,
    val value: String
)

data class SampleState(
    val text: String,
    val title: String,
    val value: String
)

data class SampleTestAuditStatu(
    val text: String,
    val title: String,
    val value: String
)

data class SampleTestProces(
    val text: String,
    val title: String,
    val value: String
)

data class SampleTestType(
    val text: String,
    val title: String,
    val value: String
)

data class SendStatu(
    val text: String,
    val title: String,
    val value: String
)

data class Sex(
    val text: String,
    val title: String,
    val value: String
)

data class Statu(
    val text: String,
    val title: String,
    val value: String
)

data class StockOrderState(
    val text: String,
    val title: String,
    val value: String
)

data class StockOrderType(
    val text: String,
    val title: String,
    val value: String
)

data class TestItemDataType(
    val text: String,
    val title: String,
    val value: String
)

data class TestItemUnit(
    val text: String,
    val title: String,
    val value: String
)

data class TroubleTicketState(
    val text: String,
    val title: String,
    val value: String
)

data class UrgentLevel(
    val text: String,
    val title: String,
    val value: String
)

data class UserStatu(
    val text: String,
    val title: String,
    val value: String
)

data class UserType(
    val text: String,
    val title: String,
    val value: String
)

data class ValidStatu(
    val text: String,
    val title: String,
    val value: String
)

data class ValueLimitType(
    val text: String,
    val title: String,
    val value: String
)

data class WarnType(
    val text: String,
    val title: String,
    val value: String
)

data class WaterConfigCode(
    val text: String,
    val title: String,
    val value: String
)

data class WeekOption(
    val text: String,
    val title: String,
    val value: String
)

data class Yn(
    val text: String,
    val title: String,
    val value: String
)