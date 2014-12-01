LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_LDLIBS :=-llog
LOCAL_MODULE    := STDlibrary
LOCAL_SRC_FILES := STDlibrary.cpp

include $(BUILD_SHARED_LIBRARY)
