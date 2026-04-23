const books = require("../../data/books.js");
const cartUtil = require("../../utils/cart.js");

Page({
  data: {
    book: null,
  },

  onLoad(options) {
    const id = options.id ? Number(options.id) : null;
    const book = books.find((item) => Number(item.id) === id);

    if (!book) {
      wx.showToast({
        title: "未找到该图书",
        icon: "none",
      });
      setTimeout(() => {
        wx.navigateBack();
      }, 1200);
      return;
    }

    this.setData({ book });

    wx.setNavigationBarTitle({
      title: book.name || "图书详情",
    });
  },

  onBuyTap() {
    wx.showToast({
      title: "购买功能可后续添加",
      icon: "none",
    });
  },

  onAddCartTap() {
    const { book } = this.data;
    if (!book) return;

    cartUtil.addToCart(book, 1); // ✅ 真正加入购物车

    wx.showToast({
      title: "已加入购物车",
      icon: "success",
    });
  },
});
