package com.github.lgdd.liferay.commerce.checkout;

import com.liferay.commerce.checkout.web.util.CommerceCheckoutStep;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.log.LogService;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

@Component(
	immediate = true,
	property = {
		"commerce.checkout.step.name=" + CustomCheckoutStep.NAME,
		"commerce.checkout.step.order:Integer=" + (Integer.MAX_VALUE - 101)
	},
	service = CommerceCheckoutStep.class
)
public class CustomCheckoutStep implements CommerceCheckoutStep {

	@Override
	public String getLabel(Locale locale) {
		return "Custom Step";
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public boolean isActive(
					HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
					throws Exception {
		return true;
	}

	@Override
	public boolean isOrder() {
		return false;
	}

	@Override
	public boolean isSennaDisabled() {
		return false;
	}

	@Override
	public boolean isVisible(
					HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
					throws Exception {
		return isActive(httpServletRequest, httpServletResponse);
	}

	@Override
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
					throws Exception {
		_log.info("Process custom checkout step...");
	}

	@Override
	public void render(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
					throws Exception {

		_jspRenderer.renderJSP(
						_servletContext, httpServletRequest, httpServletResponse,
						"/checkout_step/view.jsp");
	}

	@Override
	public boolean showControls(
					HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		return true;
	}

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference(
		target = "(osgi.web.symbolicname=com.github.lgdd.liferay.commerce.checkout)"
	)
	private ServletContext _servletContext;

	private static final Log _log = LogFactoryUtil.getLog(CustomCheckoutStep.class);

	public static final String NAME = "Custom Checkout Step";
}
