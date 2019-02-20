<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/render/init.jsp" %>

<%
    CPContentHelper cpContentHelper = (CPContentHelper)request.getAttribute(CPContentWebKeys.CP_CONTENT_HELPER);

    CPCatalogEntry cpCatalogEntry = cpContentHelper.getCPCatalogEntry(request);
    CPSku cpSku = cpContentHelper.getDefaultCPSku(cpCatalogEntry);

    long cpDefinitionId = cpCatalogEntry.getCPDefinitionId();
    List<CPSku> cpSkus = cpCatalogEntry.getCPSkus();

    String quantityInputId = renderResponse.getNamespace() + cpDefinitionId + "Quantity";
%>

<div class="col-lg-12 col-md-12 col-sm-6 col-xl-6 col-xs-6">
    <div class="card">
        <div class="crop-img crop-img-bottom crop-img-center" style="height=300px">
            <a href="<%= cpContentHelper.getFriendlyURL(cpCatalogEntry, themeDisplay) %>"><img class="img img-responsive" src="<%= cpCatalogEntry.getDefaultImageFileUrl() %>"></a>
        </div>
        <div class="card-block" style="margin: 15px; text-align: center;">
            <c:if test="<%= cpSku != null %>">
                <p class="card-subtitle product-sku">
                    <liferay-ui:message arguments="<%= cpSku.getSku() %>" key="sku-x" translateArguments="<%= false %>" />
                </p>
            </c:if>

            <div>
                <div>
                    <h3 style="margin: 0;">
                        <a href="<%= cpContentHelper.getFriendlyURL(cpCatalogEntry, themeDisplay) %>"> <%= cpCatalogEntry.getName() %> </a>
                    </h3>
                </div>
            </div>
            <div class="product-price" style="margin-top: 10px;">
				<span class="commerce-price">
					<b><i></i><liferay-commerce:price CPDefinitionId="<%= cpDefinitionId %>" discountLabel="<%= LanguageUtil.get(request, "you-save") %>" /></i></b>
				</span>
            </div>
            <div class="product-actions" style="margin-top: 10px;">
                <c:if test="<%= cpCatalogEntry.isIgnoreSKUCombinations() %>">
                    <div class="autofit-row">
                        <div class="autofit-col">
                            <liferay-commerce:quantity-input
                                    CPDefinitionId="<%= cpDefinitionId %>"
                                    useSelect="<%= false %>"
                            />
                        </div>

                        <div class="autofit-col autofit-col-expand">
                            <liferay-commerce-cart:add-to-cart
                                    CPDefinitionId="<%= cpDefinitionId %>"
                                    CPInstanceId="<%= (cpSku == null) ? 0 : cpSku.getCPInstanceId() %>"
                                    elementClasses="btn-block btn-primary text-truncate"
                                    taglibQuantityInputId="<%= quantityInputId %>"
                            />
                        </div>
                    </div>
                </c:if>

                <c:if test="<%= !cpCatalogEntry.isIgnoreSKUCombinations() %>">
                    <a class="btn btn-primary text-truncate" href="<%= cpContentHelper.getFriendlyURL(cpCatalogEntry, themeDisplay) %>">Order with options</a>
                </c:if>
            </div>
        </div>




        <div class="product-footer">
            <div class="product-subactions">
                <c:choose>
                    <c:when test="<%= cpCatalogEntry.isIgnoreSKUCombinations() %>">
                        <div class="autofit-row">
                            <div class="autofit-col autofit-col-expand">
                                <div class="product-list-info-compare">
                                    <liferay-commerce:compare-product CPDefinitionId="<%= cpDefinitionId %>" />
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="autofit-row">
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
