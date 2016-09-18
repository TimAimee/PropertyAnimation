# PropertyAnimation

自定义柱状图，并实现了3种属性动画（横向，纵向，横纵混合）

# 横向,主要实现代码

	  for (int i = 0; i < mBarCount; i++) {
            if (i < count * mBarProgress) {
                float maginTop = getMaginTop(mStepArray[i]);
                RectF recf = new RectF((mBarWidth + mBarMaginLeft) * i, mBarMaxHeight - maginTop + mBarMaginTop,
                        mBarWidth * (i + 1) + mBarMaginLeft * i, mBarMaxHeight + mBarMaginTop);
                canvas.drawRoundRect(recf, roundWidthX, roundWidthY, mBarPaint);
            }
        }		 

![横向](http://7u2mnh.com1.z0.glb.clouddn.com/property_hor.gif)
 
# 纵向,主要实现代码

	for (int i = 0; i < mBarCount; i++) {
			if (i < count) {
				float maginTop = getMaginTop(mStepArray[i]);
				RectF recf = new RectF((mBarWidth + mBarMaginLeft) * i, mBarMaxHeight-(mBarMaxHeight-maginTop)*mBarProgress  + mBarMaginTop,
						mBarWidth * (i + 1) + mBarMaginLeft * i, mBarMaxHeight + mBarMaginTop);
				canvas.drawRoundRect(recf, roundWidthX, roundWidthY, mBarPaint);
			}
		}


![纵向](http://7u2mnh.com1.z0.glb.clouddn.com/property_ver.gif)

# 横纵混合,主要实现代码

	for (int i = 0; i < mBarCount*mBarProgress; i++) {
			if (i < count) {
				float maginTop = getMaginTop(mStepArray[i]);
				RectF recf = new RectF((mBarWidth + mBarMaginLeft) * i, mBarMaxHeight-(mBarMaxHeight-maginTop)*mBarProgress  + mBarMaginTop,
						mBarWidth * (i + 1) + mBarMaginLeft * i, mBarMaxHeight + mBarMaginTop);
				canvas.drawRoundRect(recf, roundWidthX, roundWidthY, mBarPaint);
			}
		}

![横纵混合](http://7u2mnh.com1.z0.glb.clouddn.com/property_HorandVer.gif)
