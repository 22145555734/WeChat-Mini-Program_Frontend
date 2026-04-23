// miniprogram/pages/order-list/order-list.js
const { getOrders } = require("../../utils/storage");

function formatTime(ts) {
  const d = new Date(ts);
  const y = d.getFullYear();
  const m = String(d.getMonth() + 1).padStart(2, "0");
  const day = String(d.getDate()).padStart(2, "0");
  const h = String(d.getHours()).padStart(2, "0");
  const min = String(d.getMinutes()).padStart(2, "0");
  return `${y}-${m}-${day} ${h}:${min}`;
}

function statusText(status) {
  switch (status) {
    case "PAID":
      return "已支付";
    case "CANCELED":
      return "已取消";
    case "PENDING":
    default:
      return "待支付";
  }
}

Page({
  data: {
    orders: [],
  },

  onShow() {
    const orders = getOrders().map((o) => ({
      ...o,
      createTimeText: formatTime(o.createTime),
      statusText: statusText(o.status),
      totalPriceText: Number(o.totalPrice || 0).toFixed(2),
    }));

    this.setData({ orders });
  },

  goShopping() {
    wx.switchTab({
      url: "/pages/home/home",
    });
  },
});
