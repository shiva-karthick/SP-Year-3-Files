# Microsoft Developer Studio Project File - Name="lab8" - Package Owner=<4>
# Microsoft Developer Studio Generated Build File, Format Version 6.00
# ** DO NOT EDIT **

# TARGTYPE "Win32 (x86) Console Application" 0x0103

CFG=lab8 - Win32 Debug
!MESSAGE This is not a valid makefile. To build this project using NMAKE,
!MESSAGE use the Export Makefile command and run
!MESSAGE 
!MESSAGE NMAKE /f "lab8.mak".
!MESSAGE 
!MESSAGE You can specify a configuration when running NMAKE
!MESSAGE by defining the macro CFG on the command line. For example:
!MESSAGE 
!MESSAGE NMAKE /f "lab8.mak" CFG="lab8 - Win32 Debug"
!MESSAGE 
!MESSAGE Possible choices for configuration are:
!MESSAGE 
!MESSAGE "lab8 - Win32 Release" (based on "Win32 (x86) Console Application")
!MESSAGE "lab8 - Win32 Debug" (based on "Win32 (x86) Console Application")
!MESSAGE 

# Begin Project
# PROP AllowPerConfigDependencies 0
# PROP Scc_ProjName ""
# PROP Scc_LocalPath ""
CPP=cl.exe
RSC=rc.exe

!IF  "$(CFG)" == "lab8 - Win32 Release"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 0
# PROP BASE Output_Dir "Release"
# PROP BASE Intermediate_Dir "Release"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 0
# PROP Output_Dir "Release"
# PROP Intermediate_Dir "Release"
# PROP Target_Dir ""
# ADD BASE CPP /nologo /MT /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /FR /FD /c
# ADD CPP /nologo /MT /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /FR /FD /c
# ADD BASE RSC /l 0x409 /d "NDEBUG"
# ADD RSC /l 0x409 /d "NDEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 /subsystem:console /pdb:none /map /machine:I386
# ADD LINK32 /subsystem:console /pdb:none /map /machine:I386 /ETS:lab8.lnk

!ELSEIF  "$(CFG)" == "lab8 - Win32 Debug"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 1
# PROP BASE Output_Dir "Debug"
# PROP BASE Intermediate_Dir "Debug"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 1
# PROP Output_Dir "Debug"
# PROP Intermediate_Dir "Debug"
# PROP Target_Dir ""
# ADD BASE CPP /nologo /MTd /W3 /GX /Z7 /Od /D "WIN32" /D "_DEBUG" /FR /FD /c
# ADD CPP /nologo /MTd /W3 /GX /Z7 /Od /D "WIN32" /D "_DEBUG" /FR /FD /c
# ADD BASE RSC /l 0x409 /d "_DEBUG"
# ADD RSC /l 0x409 /d "_DEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 /subsystem:console /pdb:none /map /debug /machine:I386
# ADD LINK32 /subsystem:console /pdb:none /map /debug /machine:I386 /ETS:lab8.lnk

!ENDIF 

# Begin Target

# Name "lab8 - Win32 Release"
# Name "lab8 - Win32 Debug"
# Begin Source File

SOURCE=.\EcsGUI.cpp
# End Source File
# Begin Source File

SOURCE=.\HEADER.h
# End Source File
# Begin Source File

SOURCE=.\Keypad.cpp
# End Source File
# Begin Source File

SOURCE=.\lab8.cpp
# End Source File
# Begin Source File

SOURCE=.\lab8.h
# End Source File
# Begin Source File

SOURCE=lab8.lnk
# End Source File
# Begin Source File

SOURCE=.\LCD.CPP
# End Source File
# Begin Source File

SOURCE=rtos.ini
# End Source File
# Begin Source File

SOURCE=.\SPEAKER.CPP
# End Source File
# End Target
# End Project
