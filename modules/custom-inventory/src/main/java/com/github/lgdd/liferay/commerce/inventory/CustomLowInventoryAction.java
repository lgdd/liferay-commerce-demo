package com.github.lgdd.liferay.commerce.inventory;

import com.liferay.commerce.model.CommerceWarehouseItem;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.stock.activity.CommerceLowStockActivity;
import com.liferay.portal.kernel.exception.PortalException;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Locale;

@Component(
	immediate = true,
	property = {
					"commerce.low.stock.activity.key=" + CustomLowInventoryAction.KEY,
					"commerce.low.stock.activity.priority:Integer=10"
	},
	service = CommerceLowStockActivity.class
)
public class CustomLowInventoryAction implements CommerceLowStockActivity {

	@Override
	public void execute(CommerceWarehouseItem commerceWarehouseItem)
					throws PortalException {
		CPInstance cpInstance = commerceWarehouseItem.getCPInstance();

		if (cpInstance.isPublished()) {
			cpInstance.setPublished(false);

			_cpInstanceLocalService.updateCPInstance(cpInstance);
		}
	}

	@Reference
	CPInstanceLocalService _cpInstanceLocalService;

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getLabel(Locale locale) {
		return DEFAULT_LABEL;
	}

	public static final String KEY = "custom-commerce-low-inventory-action";
	public static final String DEFAULT_LABEL = "Custom Low Inventory Action";
}
