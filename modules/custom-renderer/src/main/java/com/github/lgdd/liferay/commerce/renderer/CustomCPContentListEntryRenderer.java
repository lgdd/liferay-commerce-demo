package com.github.lgdd.liferay.commerce.renderer;

import com.liferay.commerce.product.constants.CPPortletKeys;
import com.liferay.commerce.product.content.render.list.entry.CPContentListEntryRenderer;
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
		"commerce.product.content.list.entry.renderer.key=" + CustomCPContentListEntryRenderer.KEY,
		"commerce.product.content.list.entry.renderer.order=1000",
		"commerce.product.content.list.entry.renderer.portlet.name=" + CPPortletKeys.CP_PUBLISHER_WEB,
		"commerce.product.content.list.entry.renderer.portlet.name=" + CPPortletKeys.CP_SEARCH_RESULTS,
		"commerce.product.content.list.entry.renderer.type=grouped",
		"commerce.product.content.list.entry.renderer.type=simple",
		"commerce.product.content.list.entry.renderer.type=virtual"
	},
	service = CPContentListEntryRenderer.class
)
public class CustomCPContentListEntryRenderer implements CPContentListEntryRenderer {

	@Override public String getKey() {
		return KEY;
	}

	@Override public String getLabel(Locale locale) {
		return DEFAULT_LABEL;
	}

	@Override public void render(
					HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
					throws Exception {
		_jspRenderer.renderJSP(
						_servletContext, httpServletRequest, httpServletResponse,
						"/render/list_entry/search_result.jsp");
	}

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference(
		target = "(osgi.web.symbolicname=com.github.lgdd.liferay.commerce.renderer)"
	)
	private ServletContext _servletContext;

	public static final String KEY = "custom-product-list-renderer";
	public static final String DEFAULT_LABEL = "Custom Product List Renderer";
}
