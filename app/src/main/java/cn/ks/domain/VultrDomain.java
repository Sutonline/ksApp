package cn.ks.domain;

import java.util.List;

import lombok.Data;

/**
 * @author yongkang.zhang
 * @date 2017/12/8
 */
@Data
public class VultrDomain {

    private String subid;
    private BasicInfo basicInfo;
    private BandWidthVo bandwidthVo;

    /**
     * server的基本信息
     */
    @Data
    public static class BasicInfo {

        private String os;
        private String main_ip;
        private String location;
        private String cost_per_month;
        private String current_bandwidth_gb;
        private String allowed_bandwidth_gb;
        private String power_status;
        private String server_status;
        private String label;
        private String tag;
    }

    @Data
    public static class BandWidthItem {
        private String date;
        private String bytes;
    }

    @Data
    public static class BandWidthVo {
        private List<BandWidthItem> incomingBytes;
        private List<BandWidthItem> outgoingBytes;
    }
}
