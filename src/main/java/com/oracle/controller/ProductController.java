package com.oracle.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import com.oracle.model.PurchaseOrder;
import com.oracle.model.PurchaseOrderItems;
import com.oracle.repositories.ProductRepository;
import com.oracle.repositories.PurchaseOrderItemsRepository;
import com.oracle.repositories.PurchaseOrderRepository;


@Controller
@RequestMapping("/")
public class ProductController {
	private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);
	private ProductRepository productRepository;
	@Autowired
	private ChildProductRepository childProductRepository;

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;

	@Autowired
	private PurchaseOrderItemsRepository purchaseOrderItemsRepository;

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
		map.put("TabActive", "Products");
		return "products";
	}

	@RequestMapping(value = "/childProducts", method = RequestMethod.GET)
	public String getChildProductsByParentProductId(@RequestParam("productId")Long productId, HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		List<ChildProduct> childProducts = childProductRepository.findByParentProductId(productId);
		map.put("childProducts",childProducts);
		LOG.info(childProducts.toString());
		map.put("TabActive", "Products");
		return "childProducts";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addToCart", method = RequestMethod.POST)
	public String addToCart(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		String productsInCart = request.getParameter("productsInCart");
		map.put("productsInCart", productsInCart);
		request.getSession().setAttribute("productsInCart", productsInCart);
		LOG.info(productsInCart);
		List<String> productList = new ArrayList<>();
		List<String> sessionProductList = (List<String>) request.getSession().getAttribute("productList");
		if (productsInCart != null) 
		for (String product : productsInCart.split(",")) {
			if (product != "")
				productList.add(product);
		}
		if(sessionProductList==null) {
			sessionProductList = new ArrayList<>();
			sessionProductList.addAll(productList);
			LOG.info(sessionProductList.toString());
		} else {
			sessionProductList.addAll(productList);
			LOG.info(sessionProductList.toString());			
		}
		map.put("productList", sessionProductList);
		request.getSession().setAttribute("productList", productList);
		map.put("TabActive", "Products");
		return "redirect:products";
	}

	@RequestMapping(value = "/openCart", method = RequestMethod.GET)
	public String openCart(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		List<String> productList = (List<String>) request.getSession().getAttribute("productList");
		if (productList!=null && productList.size()>0) {
			List<Product> products = productRepository.findAllProductsByProductCode(productList);
			LOG.info(products.toString());
			List<ProductBean> cartList = new ArrayList<>();
			long lineNo=0;
			cartList.addAll(products.stream().map(pm -> {
				ProductBean bean = new ProductBean();
				bean.setCost(pm.getCost());
				bean.setDescription(pm.getDescription());
				bean.setId(pm.getId());
				bean.setMoq(pm.getMoq());
				bean.setName(pm.getName());
				bean.setProductCode(pm.getProductCode());
				bean.setQuantity(1);
				return bean;
			}).collect(Collectors.toList()));
			List<ChildProduct> childProducts = childProductRepository.findAllProductsByProductCode(productList);
			LOG.info(childProducts.toString());
			cartList.addAll(childProducts.stream().map(pm -> {
				ProductBean bean = new ProductBean();
				bean.setCost(pm.getCost());
				bean.setDescription(pm.getDescription());
				bean.setId(pm.getId());
				bean.setMoq(pm.getMoq());
				bean.setName(pm.getName());
				bean.setProductCode(pm.getProductCode());
				bean.setQuantity(1);
				return bean;
			}).collect(Collectors.toList()));
			double totalPrice=0;
			for (ProductBean list : cartList) {
				list.setLineNo(++lineNo);
				totalPrice+=list.getCost();
			}
			map.put("sizeOfList", cartList.size());
			map.put("totalPrice", totalPrice);
			LOG.info("cartList:"+cartList);
			map.put("cartList", cartList);
			request.getSession().setAttribute("cartList", cartList);
		} else {
			map.put("TabActive", "Products");
			return "redirect:products";
		}
		map.put("TabActive", "OpenCart");
		return "openCart";
	}
	@RequestMapping(value = "/createPO", method = RequestMethod.POST)
	public String createPO(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		List<ProductBean> cartList = (List<ProductBean>) request.getSession().getAttribute("cartList");
		List<PurchaseOrderItems> items = new ArrayList<>();
		double totalPrice = 0;
		for (ProductBean productBean : cartList) {
			if (request.getParameter("quantity"+productBean.getLineNo()) != null) {
				int qty = Integer.parseInt(request.getParameter("quantity"+productBean.getLineNo()));
				productBean.setQuantity(qty);
				productBean.setTotalCostByProduct(productBean.getQuantity()*productBean.getCost());
				totalPrice += productBean.getTotalCostByProduct();
			}
		}
		LOG.info(cartList.toString());
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		Date date = new Date();
		purchaseOrder.setCreatedDate(date+"");
		String poNumber = "PO"+date.getYear()+date.getMonth()+date.getDay()+date.getTime();
		purchaseOrder.setPurchaseOrderNumber(poNumber);
		purchaseOrder.setTotalPrice(totalPrice);
		PurchaseOrder pOrder = purchaseOrderRepository.save(purchaseOrder);
		for (ProductBean productBean : cartList) {
			PurchaseOrderItems item = new PurchaseOrderItems();
			item.setLineNo(productBean.getLineNo());
			item.setPoId(pOrder.getPoId());
			item.setProductId(productBean.getId());
			item.setQuantity(productBean.getQuantity());
			purchaseOrderItemsRepository.save(item);
		}
		request.getSession().setAttribute("cartList",null);
		request.getSession().setAttribute("productList", null);
		request.getSession().setAttribute("PurchaseOrderList", purchaseOrderRepository.findAll());
		map.put("TabActive", "OrderHistory");
		return "orderHistory";
	}
	
	@RequestMapping(value = "/orderHistory", method = RequestMethod.GET)
	public String orderHistory(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		request.getSession().setAttribute("cartList",null);
		request.getSession().setAttribute("productList", null);
		map.put("PurchaseOrderList", purchaseOrderRepository.findAll());
		map.put("TabActive", "OrderHistory");
		return "orderHistory";
	}
	@RequestMapping(value = "/viewPO", method = RequestMethod.GET)
	public String viewPO(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		String poNumber = request.getParameter("purchaseOrderNumber");
		com.oracle.beans.PurchaseOrder purchaseOrder = new com.oracle.beans.PurchaseOrder();
		List<ProductBean> productBeans = new ArrayList<>();
		if (poNumber != null) {
			List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAllPOByPurchaseOrderNumber(poNumber);
			if(purchaseOrders != null && purchaseOrders.size()==1) {
				purchaseOrder.setPoId(purchaseOrders.get(0).getPoId());
				purchaseOrder.setPurchaseOrderNumber(poNumber);
				purchaseOrder.setTotalPrice(purchaseOrders.get(0).getTotalPrice());
				purchaseOrder.setCreatedDate(purchaseOrders.get(0).getCreatedDate());
			}
			List<PurchaseOrderItems> purchaseOrderItems = purchaseOrderItemsRepository.findAllByPoId(purchaseOrder.getPoId());
			productBeans = purchaseOrderItems.stream().map(pm -> {
				Optional<Product> bean = productRepository.findById(pm.getProductId());
				ProductBean pb = new ProductBean();
				pb.setCost(bean.get().getCost());
				pb.setDescription(bean.get().getDescription());
				pb.setId(bean.get().getId());
				pb.setLineNo(pm.getLineNo());
				pb.setMoq(bean.get().getMoq());
				pb.setName(bean.get().getName());
				pb.setProductCode(bean.get().getProductCode());
				pb.setQuantity(pm.getQuantity());
				pb.setTotalCostByProduct(pm.getQuantity()*bean.get().getCost());
				return pb;
			}).collect(Collectors.toList());
			LOG.info("pb"+productBeans.toString());
			purchaseOrder.setProductBeans(productBeans);
			map.put("ViewPO", true);
			map.put("purchaseOrder", purchaseOrder);
			map.put("cartList", purchaseOrder.getProductBeans());
		}
		map.put("TabActive", "OrderHistory");
		return "openCart";
	}

}
