package cn.iocoder.yudao.module.member.service.address;

import cn.iocoder.yudao.module.member.controller.app.address.vo.AppAddressCreateReqVO;
import cn.iocoder.yudao.module.member.controller.app.address.vo.AppAddressUpdateReqVO;
import cn.iocoder.yudao.module.member.dal.dataobject.address.AddressDO;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户收件地址 Service 接口
 *
 * @author 芋道源码
 */
public interface AddressService {

    /**
     * 创建用户收件地址
     *
     *
     * @param userId 用户编号
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAddress(String userId, @Valid AppAddressCreateReqVO createReqVO);

    /**
     * 更新用户收件地址
     *
     * @param userId 用户编号
     * @param updateReqVO 更新信息
     */
    void updateAddress(String userId, @Valid AppAddressUpdateReqVO updateReqVO);

    /**
     * 删除用户收件地址
     *
     * @param userId 用户编号
     * @param id 编号
     */
    void deleteAddress(String userId, Long id);

    /**
     * 获得用户收件地址
     *
     * @param id 编号
     * @return 用户收件地址
     */
    AddressDO getAddress(String userId, Long id);

    /**
     * 获得用户收件地址列表
     *
     * @param userId 用户编号
     * @return 用户收件地址列表
     */
    List<AddressDO> getAddressList(String userId);

    /**
     * 获得用户默认的收件地址
     *
     * @param userId 用户编号
     * @return 用户收件地址
     */
    AddressDO getDefaultUserAddress(String userId);

}
