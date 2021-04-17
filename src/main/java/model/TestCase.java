package model;

public class TestCase {
    private String element_info; //元素
    private String find_type;// id,xpath,name,classname
    private String operate_type; // click,send_keys...
    private String text; //文本框内容,或者其他内容
    private String frame; // frame的值
    private String defaultContent; //切换到默认主页面1
    private String step; //步骤
    private String case_name; //用例标题
    private String wait_time; //等待时间ms
    private String switch_window; //切换窗口1
    public void setElement_info(String element_info) {
        this.element_info = element_info;
    }

    public void setFind_type(String find_type) {
        this.find_type = find_type;
    }

    public void setOperate_type(String operate_type) {
        this.operate_type = operate_type;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public void setWait_time(String wait_time) {
        this.wait_time = wait_time;
    }

    public void setSwitch_window(String switch_window) {
        this.switch_window = switch_window;
    }

//    public void setFram(String frame) {
//        this.frame = frame;
//    }

    public void setCase_name(String case_name) {
        this.case_name = case_name;
    }

    public String getElement_info() {

        return element_info;
    }

    public String getFind_type() {
        return find_type;
    }

    public String getOperate_type() {
        return operate_type;
    }

    public String getText() {
        return text;
    }




    public String getDefaultContent() {
        return defaultContent;
    }

    public String getStep() {
        return step;
    }

    public void setDefaultContent(String defaultContent) {
        this.defaultContent = defaultContent;
    }

    public String getCaseName() {
        return case_name;
    }

    public String getFrame() {
        return frame;
    }

    public String getWait_time() {
        return wait_time;
    }

    public String getSwitch_window() {
        return switch_window;
    }
}
