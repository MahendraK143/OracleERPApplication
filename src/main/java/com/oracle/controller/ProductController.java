package com.oracle.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.beans.ProductBean;
import com.oracle.model.ChildProduct;
import com.oracle.repositories.ChildProductRepository;
import com.oracle.util.ModelToObjectCoverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.oracle.model.Product;
import com.oracle.repositories.ProductRepository;


@Controller
@RequestMapping("/")
public class ProductController {
	private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);
	private ProductRepository productRepository;
	@Autowired
	private ChildProductRepository childProductRepository;

	@Autowired
	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String getAllProject(HttpServletRequest request, HttpServletResponse response,ModelMap map) {
		List<Product> productsModel = productRepository.findAll();
		List<ProductBean> productBeans = ModelToObjectCoverter.convertProductModelToBean(productsModel);
		productBeans = productsModel.stream().map(pm -> {
			ProductBean bean = new ProductBean();
			bean.setCost(pm.getCost());
			bean.setDescription(pm.getDescription());
			bean.setId(pm.getId());
			bean.setMoq(pm.getMoq());
			bean.setName(pm.getName());
			bean.setProductCode(pm.getProductCode());
			bean.setChildProducts(childProductRepository.findByParentProductId(pm.getId()));
			return bean;
		}).collect(Collectors.toList());

		if (productBeans != null) {
			map.addAttribute("products", productBeans);
		}
		request.getSession().setAttribute("productBeans",productBeans);
		LOG.info(productBeans.toString());
		return "products";
	}

	@RequestMapping(value = "/childProducts", method = RequestMethod.GET)
	public String getChildProductsByParentProductId(@RequestParam("productId")Long productId, HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		List<ChildProduct> childProducts = childProductRepository.findByParentProductId(productId);
		map.put("childProducts",childProducts);
		LOG.info(childProducts.toString());
		return "childProducts";
	}


}
