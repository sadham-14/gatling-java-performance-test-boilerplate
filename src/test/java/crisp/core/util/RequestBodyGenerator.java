package crisp.core.util;

import org.json.JSONObject;

public class RequestBodyGenerator {
    public static String getSubmitRequestBodyPayload() {
        JSONObject object = new JSONObject();
        object.put("type", "create_env_app");

        JSONObject data = new JSONObject();
        JSONObject applicationEnvData = new JSONObject();
        applicationEnvData.put("projectCode", "sqwp4");
        applicationEnvData.put("name", "SGWorkpass DEV");
        applicationEnvData.put("awsAccId", "508939603901");
        applicationEnvData.put("vpcId", "vpc-0a01f33d8e304c4bb");

        String[] vpcCidr = {"100.31.3.0/24"};
        applicationEnvData.put("vpcCidr", vpcCidr);

        applicationEnvData.put("envFlag", "dev");
        applicationEnvData.put("deploymentMethod", 1);

        String[] zoneFlag = {"internet"};
        applicationEnvData.put("zoneFlag", zoneFlag);

        String[] azFlag = {"ap-southeast-1a", "ap-southeast-1b"};
        applicationEnvData.put("azFlag", azFlag);

        applicationEnvData.put("agencyEnv", 1);
        applicationEnvData.put("managementTier", 6);
        applicationEnvData.put("perimeterTier", 4);
        applicationEnvData.put("devopsTier", 2);

        JSONObject subnets = new JSONObject();
        JSONObject tgwASubnet = new JSONObject().put("cidr", "100.31.3.0/28");
        JSONObject tgwBSubnet = new JSONObject().put("cidr", "100.31.3.16/28");
        JSONObject webASubnet = new JSONObject().put("cidr", "100.31.3.32/28");
        JSONObject webBSubnet = new JSONObject().put("cidr", "100.31.3.48/28");
        JSONObject appASubnet = new JSONObject().put("cidr", "100.31.3.64/28");
        JSONObject appBSubnet = new JSONObject().put("cidr", "100.31.3.80/28");
        JSONObject serverlessASubnet = new JSONObject().put("cidr", "100.31.3.96/28");
        JSONObject serverlessBSubnet = new JSONObject().put("cidr", "100.31.3.112/28");
        JSONObject dbASubnet = new JSONObject().put("cidr", "100.31.3.128/28");
        JSONObject dbBSubnet = new JSONObject().put("cidr", "100.31.3.144/28");

        subnets.put("tgwASubnet", tgwASubnet);
        subnets.put("tgwBSubnet", tgwBSubnet);
        subnets.put("webASubnet", webASubnet);
        subnets.put("webBSubnet", webBSubnet);
        subnets.put("appASubnet", appASubnet);
        subnets.put("appBSubnet ", appBSubnet);
        subnets.put("serverlessASubnet", serverlessASubnet);
        subnets.put("serverlessBSubnet", serverlessBSubnet);
        subnets.put("dbASubnet", dbASubnet);
        subnets.put("dbBSubnet", dbBSubnet);
        applicationEnvData.put("subnets", subnets);

        data.put("applicationEnvData", applicationEnvData);
        object.put("data", data);

        return object.toString();
    }

    public static String getApproveRequestBody() {
        JSONObject object = new JSONObject();
        object.put("id", "#{requestId}");

        return object.toString();
    }
}
