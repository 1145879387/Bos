INSERT INTO `auth_function` VALUES ('11', '基础档案', 'jichudangan', NULL, NULL, '1', '0', NULL);
INSERT INTO `auth_function` VALUES ('112', '收派标准', 'standard', NULL, 'page_base_standard.action', '1', '1', '11');
INSERT INTO `auth_function` VALUES ('113', '取派员设置', 'staff', NULL, 'page_base_staff.action', '1', '2', '11');
INSERT INTO `auth_function` VALUES ('114', '区域设置', 'region', NULL, 'page_base_region.action', '1', '3', '11');
INSERT INTO `auth_function` VALUES ('115', '管理分区', 'subarea', NULL, 'page_base_subarea.action', '1', '4', '11');
INSERT INTO `auth_function`
VALUES ('116', '管理定区/调度排班', 'decidedzone', NULL, 'page_base_decidedzone.action', '1', '5', '11');
INSERT INTO `auth_function` VALUES ('12', '受理', 'shouli', NULL, NULL, '1', '1', NULL);
INSERT INTO `auth_function`
VALUES ('121', '业务受理', 'noticebill', NULL, 'page_qupai_noticebill_add.action', '1', '0', '12');
INSERT INTO `auth_function`
VALUES ('122', '工作单快速录入', 'quickworkordermanage', NULL, 'page_qupai_quickworkorder.action', '1', '1', '12');
INSERT INTO `auth_function`
VALUES ('124', '工作单导入', 'workordermanageimport', NULL, 'page_qupai_workorderimport.action', '1', '3', '12');
INSERT INTO `auth_function` VALUES ('13', '调度', 'diaodu', NULL, NULL, '1', '2', NULL);
INSERT INTO `auth_function` VALUES ('131', '查台转单', 'changestaff', NULL, NULL, '1', '0', '13');
INSERT INTO `auth_function` VALUES ('132', '人工调度', 'personalassign', NULL, 'page_qupai_diaodu.action', '1', '1', '13');
INSERT INTO `auth_function` VALUES ('14', '物流配送流程管理', 'zhongzhuan', NULL, NULL, '1', '3', NULL);
INSERT INTO `auth_function`
VALUES ('141', '启动配送流程', 'start', NULL, 'workOrderManageAction_list.action', '1', '0', '14');
INSERT INTO `auth_function`
VALUES ('142', '查看个人任务', 'personaltask', NULL, 'taskAction_findPersonalTask.action', '1', '1', '14');
INSERT INTO `auth_function`
VALUES ('143', '查看我的组任务', 'grouptask', NULL, 'taskAction_findGroupTask.action', '1', '2', '14');
INSERT INTO `auth_function` VALUES
  ('8a7e843355a4392d0155a43aa7150000', '删除取派员', 'staff.delete', 'xxx', 'staffAction_delete.action', '0', '1', '113');
INSERT INTO `auth_function`
VALUES ('8a7e843355a442460155a442bcfc0000', '传智播客', 'itcast', '', 'http://www.itcast.cn', '1', '1', NULL);
