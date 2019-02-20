package com.github.lgdd.liferay.commerce.renderer;

import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.content.render.CPContentRenderer;
import com.liferay.commerce.product.type.grouped.util.GroupedCPTypeHelper;
import com.liferay.commerce.product.type.virtual.util.VirtualCPTypeHelper;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Component(
	immediate = true,
	property = {
		"commerce.product.content.renderer.key=" + CustomCPContentRenderer.KEY,
		"commerce.product.content.renderer.order=1000",
		"commerce.product.content.renderer.type=grouped",
		"commerce.product.content.renderer.type=simple",
		"commerce.product.content.renderer.type=virtual"
	},
	service = CPContentRenderer.class
)
public class CustomCPContentRenderer implements CPContentRenderer {

	@Override public String getKey() {
		return KEY;
	}

	@Override public String getLabel(Locale locale) {
		return DEFAULT_LABEL;
	}

	@Override public void render(
					CPCatalogEntry cpCatalogEntry, HttpServletRequest httpServletRequest,
					HttpServletResponse httpServletResponse)
					throws Exception {


		httpServletRequest.setAttribute(
						"groupedCPTypeHelper", _groupedCPTypeHelper);
		httpServletRequest.setAttribute(
						"virtualCPTypeHelper", _virtualCPTypeHelper);

		_jspRenderer.renderJSP(
						_servletContext, httpServletRequest, httpServletResponse,
						"/render/view.jsp");
	}

	@Reference
	private GroupedCPTypeHelper _groupedCPTypeHelper;

	@Reference
	private VirtualCPTypeHelper _virtualCPTypeHelper;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference(
		target = "(osgi.web.symbolicname=com.github.lgdd.liferay.commerce.renderer)"
	)
	private ServletContext _servletContext;

	public static final String KEY = "custom-product-detail-renderer";

	public static final String DEFAULT_LABEL = "Custom Product Detail Renderer";
}
