package com.surgeon.weaving.plugin

class SurgeonExtension {
  def enabled = true

  def setEnabled(boolean enabled) {
    this.enabled = enabled
  }

  def getEnabled() {
    return enabled;
  }
}
