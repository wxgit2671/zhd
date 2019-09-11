package com.zhd.serviceprovider.entity.user;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户信息类
 *
 * @author lizhichao
 */

@SuppressWarnings("serial")
public class UserInfo {
    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * @Fields : 用户id
     */
    private Long userId;
    /**
     * @Fields : 用户姓名
     */
    private String name;
    /**
     * @Fields : 用户身份证号
     */
    private String idcard;
    /**
     * @Fields : 用户身份证省份信息
     */
    private String idcardProvince;
    /**
     * @Fields : 用户身份证市信息
     */
    private String idcardCity;
    /**
     * @Fields : 用户身份证区县信息
     */
    private String idcardArea;
    /**
     * @Fields : 用户身份证地址信息
     */
    private String idcardAddress;
    /**
     * @Fields : 用户id
     */
    private String nativePlace;
    /**
     * @Fields : 用户性别
     */
    private String sex;
    /**
     * @Fields : 用户生日
     */
    private Date birthday;
    /**
     * @Fields : 用户年龄
     */
    private Integer age;
    /**
     * @Fields : 用户手机号
     */
    private String phone;
    /**
     * @Fields : 手机号码归属地
     */
    private String phoneRegion;
    /**
     * @Fields : 用户地址
     */
    private String address;
    /**
     * @Fields : 用户婚姻信息
     */
    private String marriage;
    private String marriageName;
    /**
     * @Fields : 用户子女信息
     */
    private String children;
    private String childrenName;
    /**
     * @Fields : QQ号码
     */
    private String qq;
    /**
     * @Fields : 常用邮箱
     */
    private String email;
    /**
     * @Fields : imei
     */
    private String imei;
    /**
     * @Fields : imsi
     */
    private String imsi;
    /**
     * @Fields : 手机品牌
     */
    private String phoneBrand;
    /**
     * @Fields : 手机型号
     */
    private String phoneModel;
    /**
     * @Fields : 手机操作系统
     */
    private String phoneOs;
    /**
     * @Fields : app版本
     */
    private String appVersion;
    /**
     * @Fields : 用户信用评分
     */
    private Integer score;
    /**
     * @Fields : 用户生活半径
     */
    private String lifeRadius;

    /**
     * @Fields : 用户生活半径名称，不存数据库
     */
    private String lifeRadiusName;

    private String idcardPhoto;

    /**
     * 渠道来源code
     */
    private String channelCode;

    /**
     * 渠道来源Str - 不保存
     */
    private String channelStr;
    /**
     * @Dscrription:状态集合
     * @author: haidong
     * @date: 2016年2月4日 上午11:14:34
     */
    private String[] notInfoStates;

    /**
     * @Fields idcardAuthen : 身份证认证状态
     */
    private String idcardAuthen;

    /**
     * @Fields contract : 借款总额
     * @author: 张元元
     * @date: 2016年1月22日 下午
     */
    private BigDecimal amount;
    /**
     * 人脸与身份证照片匹配度
     */
    private BigDecimal idcardFaceCertConfidence;
    /**
     * 图片附件id
     */
    private Long attachmentId;

    /**
     * 总结余
     */
    private BigDecimal totalBalance;

    /**
     * 是否有借款详情 ，不存
     */
    private String infoView;
    /**
     * 是否同步
     */
    private String isSync;
    /**
     * @Fields classification : 客户分类值
     */
    private String classification;
    /**
     * @Fields hackAuhen : 防黑认证结果
     */
    private String hackAuthen;
    /**
     * 真信审核结果
     */
    private Integer recommendCode;
    /**
     * 真信审核状态位
     */
    private String recommendMsg;
    /**
     * 真信审核结果说明
     */
    private String remark;
    /**
     * 借款申请时间点的评分
     */
    private Integer applyScore;
    /**
     * 借款申请时间点的客户分类
     */
    private Integer applyClass;
    /**
     * 真信数据是否是人工审核的
     */
    private String handmade;
    /**
     * 可疑用户标示
     */
    private Integer suspicious;
    // 用户借款额度
    private BigDecimal currentQuota;

    /**
     * 是否是信数华道网贷黑名单
     */
    private String xshdBlack;
    /*
     *海象userId
     */
    private String hxUserId;

    private UserInfo inviteUserInfo;

    private Integer loanSuccessTimes;

    /**
     * 身份证有效期
     *
     * @return
     */
    private Date idcardTimeLimit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLoanSuccessTimes() {
        return loanSuccessTimes;
    }

    public void setLoanSuccessTimes(Integer loanSuccessTimes) {
        this.loanSuccessTimes = loanSuccessTimes;
    }


    public UserInfo getInviteUserInfo() {
        return inviteUserInfo;
    }

    public void setInviteUserInfo(UserInfo inviteUserInfo) {
        this.inviteUserInfo = inviteUserInfo;
    }


    public String getHxUserId() {
        return hxUserId;
    }

    public void setHxUserId(String hxUserId) {
        this.hxUserId = hxUserId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getLifeRadius() {
        return lifeRadius;
    }

    public void setLifeRadius(String lifeRadius) {
        this.lifeRadius = lifeRadius;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public String getIdcardAddress() {
        return idcardAddress;
    }

    public void setIdcardAddress(String idcardAddress) {
        this.idcardAddress = idcardAddress;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getPhoneBrand() {
        return phoneBrand;
    }

    public void setPhoneBrand(String phoneBrand) {
        this.phoneBrand = phoneBrand;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getIdcardProvince() {
        return idcardProvince;
    }

    public String getPhoneRegion() {
        return phoneRegion;
    }

    public void setPhoneRegion(String phoneRegion) {
        this.phoneRegion = phoneRegion;
    }

    public void setIdcardProvince(String idcardProvince) {
        this.idcardProvince = idcardProvince;
    }

    public String getIdcardCity() {
        return idcardCity;
    }

    public void setIdcardCity(String idcardCity) {
        this.idcardCity = idcardCity;
    }

    public String getIdcardArea() {
        return idcardArea;
    }

    public void setIdcardArea(String idcardArea) {
        this.idcardArea = idcardArea;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getPhoneOs() {
        return phoneOs;
    }

    public void setPhoneOs(String phoneOs) {
        this.phoneOs = phoneOs;
    }

    public String getIdcardAuthen() {
        return idcardAuthen;
    }

    public void setIdcardAuthen(String idcardAuthen) {
        this.idcardAuthen = idcardAuthen;
    }

    public String getIdcardPhoto() {
        return idcardPhoto;
    }

    public void setIdcardPhoto(String idcardPhoto) {
        this.idcardPhoto = idcardPhoto;
    }

    public String getLifeRadiusName() {
        return lifeRadiusName;
    }

    public void setLifeRadiusName(String lifeRadiusName) {
        this.lifeRadiusName = lifeRadiusName;
    }

    public String[] getNotInfoStates() {
        return notInfoStates;
    }

    public void setNotInfoStates(String[] notInfoStates) {
        this.notInfoStates = notInfoStates;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelStr() {
        return channelStr;
    }

    public void setChannelStr(String channelStr) {
        this.channelStr = channelStr;
    }

    public String getMarriageName() {
        return marriageName;
    }

    public void setMarriageName(String marriageName) {
        this.marriageName = marriageName;
    }

    public String getChildrenName() {
        return childrenName;
    }

    public void setChildrenName(String childrenName) {
        this.childrenName = childrenName;
    }

    public BigDecimal getIdcardFaceCertConfidence() {
        return idcardFaceCertConfidence;
    }

    public void setIdcardFaceCertConfidence(BigDecimal idcardFaceCertConfidence) {
        this.idcardFaceCertConfidence = idcardFaceCertConfidence;
    }

    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    public String getInfoView() {
        return infoView;
    }

    public void setInfoView(String infoView) {
        this.infoView = infoView;
    }

    public String getIsSync() {
        return isSync;
    }

    public void setIsSync(String isSync) {
        this.isSync = isSync;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getHackAuthen() {
        return hackAuthen;
    }

    public void setHackAuthen(String hackAuthen) {
        this.hackAuthen = hackAuthen;
    }

    public Integer getRecommendCode() {
        return recommendCode;
    }

    public void setRecommendCode(Integer recommendCode) {
        this.recommendCode = recommendCode;
    }

    public String getRecommendMsg() {
        return recommendMsg;
    }

    public void setRecommendMsg(String recommendMsg) {
        this.recommendMsg = recommendMsg;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getApplyScore() {
        return applyScore;
    }

    public void setApplyScore(Integer applyScore) {
        this.applyScore = applyScore;
    }

    public Integer getApplyClass() {
        return applyClass;
    }

    public void setApplyClass(Integer applyClass) {
        this.applyClass = applyClass;
    }

    public String getHandmade() {
        return handmade;
    }

    public void setHandmade(String handmade) {
        this.handmade = handmade;
    }

    public Integer getSuspicious() {
        return suspicious;
    }

    public void setSuspicious(Integer suspicious) {
        this.suspicious = suspicious;
    }

    public BigDecimal getCurrentQuota() {
        return currentQuota;
    }

    public void setCurrentQuota(BigDecimal currentQuota) {
        this.currentQuota = currentQuota;
    }

    public String getXshdBlack() {
        return xshdBlack;
    }

    public void setXshdBlack(String xshdBlack) {
        this.xshdBlack = xshdBlack;
    }

    public Date getIdcardTimeLimit() {
        return idcardTimeLimit;
    }

    public void setIdcardTimeLimit(Date idcardTimeLimit) {
        this.idcardTimeLimit = idcardTimeLimit;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
