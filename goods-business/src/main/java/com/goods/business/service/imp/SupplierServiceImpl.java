package com.goods.business.service.imp;

import com.goods.business.converter.SupplierConverter;
import com.goods.business.mapper.SupplierMapper;
import com.goods.business.service.SupplierService;
import com.goods.common.model.business.Supplier;
import com.goods.common.vo.business.SupplierVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JaChen
 * @date 2023/4/3 13:55
 */
@Service
@SuppressWarnings("all")
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public List<SupplierVO> findSupplierList(SupplierVO supplierVO) {
        if (supplierVO == null) {
            return null;
        }
        Supplier supplier = SupplierConverter.convertSupplier(supplierVO);
        // 空条件情况
        if (supplierVO.getContact()==null&&supplierVO.getName()==null&&supplier.getAddress()==null){
            List<Supplier> suppliers = supplierMapper.selectAll();
            // 转换VO
            List<SupplierVO> supplierVOS = SupplierConverter.convertSupplierList(suppliers);
            return supplierVOS;
        }
        // 模糊条件查询
        Example example = new Example(Supplier.class);
        example.createCriteria().andLike("name", "%"+supplier.getName() + "%")
                .andLike("address", "%"+supplier.getAddress() + "%")
                .andLike("contact","%"+supplier.getContact() + "%");
        List<Supplier> supplierList = supplierMapper.selectByExample(example);
        // 判空
        if (supplierList == null || supplierList.size() == 0) {
            return null;
        }
        // 转换VO
        List<SupplierVO> supplierVOS = SupplierConverter.convertSupplierList(supplierList);
        return supplierVOS;
    }

    @Override
    public void add(SupplierVO supplierVO) {
        supplierMapper.insert(SupplierConverter.convertSupplier(supplierVO));
    }

    @Override
    public SupplierVO get(Long id) {
        Supplier supplier = supplierMapper.selectByPrimaryKey(id);
        if (supplier != null) {
            List<Supplier> arrayList = new ArrayList<>();
            arrayList.add(supplier);
            return SupplierConverter.convertSupplierList(arrayList).get(0);
        }
        return null;
    }

    @Override
    public void update(SupplierVO supplierVO) {
        supplierMapper.updateByPrimaryKey(SupplierConverter.convertSupplier(supplierVO));
    }

    @Override
    public void delete(Long id) {
        supplierMapper.deleteByPrimaryKey(id);
    }


}
