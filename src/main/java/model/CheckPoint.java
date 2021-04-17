package model;

/***
 * 检查点实体类
 */
public class CheckPoint {
    private String element_info;//暂时没有用
    private String operate_type; //检查点方法，现在支持getValue,默认是查找,可自由拓展
    private  String find_type;//暂时无用
    private String text; //文本框内容
    private String step; //文本框内容
    private String frame; //文本框内容
    private String wait_time; //等待时间
    private String switch_window; //切换窗口
    public String getElement_info() {
        return element_info;
    }

    public void setElement_info(String element_info) {
        this.element_info = element_info;
    }

    public void setOperate_type(String operate_type) {
        this.operate_type = operate_type;
    }

    public void setFind_type(String find_type) {
        this.find_type = find_type;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public void setWait_time(String wait_time) {
        this.wait_time = wait_time;
    }

    public void setSwitch_window(String switch_window) {
        this.switch_window = switch_window;
    }

    public String getOperate_type() {
        return operate_type;
    }

    public String getFind_type() {
        return find_type;
    }

    public String getText() {
        return text;
    }

    public String getStep() {
        return step;
    }

    public String getWait_time() {
        return wait_time;
    }

    public String getSwitch_window() {
        return switch_window;
    }

    public String getFrame() {
        return frame;
    }

}
