/*
 * Copyright 2014 Capptain
 * 
 * Licensed under the CAPPTAIN SDK LICENSE (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *   https://app.capptain.com/#tos
 *  
 * This file is supplied "as-is." You bear the risk of using it.
 * Capptain gives no express or implied warranties, guarantees or conditions.
 * You may have additional consumer rights under your local laws which this agreement cannot change.
 * To the extent permitted under your local laws, Capptain excludes the implied warranties of merchantability,
 * fitness for a particular purpose and non-infringement.
 */

package com.ubikod.capptain.android.sdk.reach;

import java.util.Map;

import org.json.JSONException;
import org.w3c.dom.Element;

/** Base class for all kind of announcements. */
public abstract class CapptainAbstractAnnouncement extends CapptainReachInteractiveContent
{
  /** Action's URL */
  private String mActionURL;

  /**
   * Parse an announcement.
   * @param jid service that sent the announcement.
   * @param xml raw XML of announcement to store in SQLite.
   * @param root parsed XML root DOM element.
   * @param params special parameters to inject in the action URL of the announcement.
   * @throws JSONException if a parsing error occurs.
   */
  CapptainAbstractAnnouncement(String jid, String xml, Element root, Map<String, String> params)
    throws JSONException
  {
    super(jid, xml, root);
    mActionURL = XmlUtil.getTagText(root, "url", "action");

    for (Map.Entry<String, String> param : params.entrySet())
    {
      if (mActionURL != null)
        mActionURL = mActionURL.replace(param.getKey(), param.getValue());
    }
  }

  /**
   * Get action's URL.
   * @return action's URL.
   */
  public String getActionURL()
  {
    return mActionURL;
  }
}
