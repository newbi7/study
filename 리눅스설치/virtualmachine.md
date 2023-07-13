https://jjeongil.tistory.com/1645

VirtualBox는 여러 게스트 운영 체제(가상 시스템)를 동시에 실행할 수 있는 오픈 소스 교차 플랫폼 가상화 소프트웨어입니다. 

VirtualBox는 게스트 운영 체제에 설치할 수 있는 드라이버 및 애플리케이션 집합(VirtualBox Guest Additions)을 제공합니다. 게스트 추가 기능은 게스트 시스템에 공유 폴더, 공유 클립보드, 마우스 포인터 통합, 향상된 비디오 지원 등과 같은 몇 가지 유용한 기능을 제공합니다.

Ubuntu 18.04 게스트에 VirtualBox Guest Additions를 설치하는 방법에 대해 설명합니다. Linux Mint 및 Elementary OS를 포함한 Ubuntu 16.04 및 Ubuntu 기반 배포에도 동일한 지침이 적용됩니다.

 


Linux : Ubuntu 18.04 : VirtualBox Guest Additions 설치 방법, 예제, 명령어
```
Ubuntu 게스트에 게스트 추가 프로그램을 설치
VirtualBox는 지원되는 모든 게스트 운영 체제에 대해 Guest Additions 설치 관리자를 포함하는 "VBoxGuestAdditions.iso" 이미지 ISO 파일과 함께 제공됩니다. 이 파일은 호스트 시스템에 있으며 VirtualBox GUI 관리자를 사용하여 게스트 시스템에 마운트할 수 있습니다. 마운트되면 게스트 추가 설치 관리자를 사용하여 게스트 시스템에 게스트 추가 사항을 설치할 수 있습니다.

다음은 Ubuntu 게스트에 VirtualBox 게스트 추가 사항을 설치하기 위한 단계별 지침입니다. 이 단계는 Ubuntu Desktop 및 Server 게스트 설치 모두에서 작동합니다.

 

VirtualBox GUI Manager를 엽니다. 

Ubuntu 게스트 가상 시스템을 시작합니다.

Ubuntu 게스트에 sudo 사용자로 로그인하고 외부 커널 모듈 구축에 필요한 패키지를 설치합니다.

sudo apt update
sudo apt install build-essential dkms linux-headers-$(uname -r)
 

 

$(uname -r)는 실행 중인 커널 버전을 인쇄합니다.

가상 시스템 메뉴에서 아래 이미지에 표시된 대로 디바이스 -> "게스트 추가 기능 CD 이미지 삽입"을 클릭합니다.


Linux : Ubuntu 18.04 : VirtualBox Guest Additions 설치 방법, 예제, 명령어
 

 

게스트 시스템에 CD-ROM이 없다는 오류가 발생하면 가상 시스템을 중지하고 가상 시스템 설정을 열고 "스토리지" 탭에서 더하기 기호(광학 디바이스 추가)를 클릭하여 새 CD-ROM 디바이스를 시스템에 추가합니다. 완료되면 가상 시스템을 재부팅합니다. 

Ubuntu 게스트 터미널을 열고 새 디렉토리를 CD 드라이브의 마운트 지점으로 생성한 후 ISO 파일을 마운트합니다.

sudo mkdir -p /mnt/cdrom
sudo mount /dev/cdrom /mnt/cdrom
 

 

디렉토리로 이동하고 VBoxLinuxAdditions.run 스크립트를 실행하여 Guest Additions를 설치합니다. --nox11 옵션은 설치 관리자가 xterm 창을 생성하지 않도록 알려줍니다.

cd /mnt/cdrom
sudo sh ./VBoxLinuxAdditions.run --nox11

# Verifying archive integrity... All good.
# Uncompressing VirtualBox 5.2.32 Guest Additions for Linux........
# ...
# VirtualBox Guest Additions: Starting.
 

 

변경사항 적용을 위해 리부팅을 진행합니다.

sudo shutdown -r now
 

 

가상 시스템이 부팅되면 가상 시스템에 로그인하고 설치에 성공했으며 lsmod 명령을 사용하여 커널 모듈이 로드되었는지 확인합니다.

출력 없음은 VirtualBox 커널 모듈이 로드되지 않았음을 의미합니다.

lsmod | grep vboxguest

# vboxguest             303104  2 vboxsf
 

 

이제 VirtualBox 게스트 추가 프로그램이 Ubuntu 게스트 시스템에 설치되었으므로 가상 시스템 설정 "스토리지" 탭에서 공유 클립보드 및 끌어서 놓기 지원을 사용하도록 설정하고 "디스플레이" 탭에서 3D 가속을 사용하도록 설정하고 공유 폴더를 만드는 등의 작업을 수행할 수 있습니다.

Ubuntu 18.04 가상 시스템에 VirtualBox Guest Additions를 설치하는 것은 쉬운 작업입니다. Guest Additions는 게스트 운영 체제를 최적화하여 성능을 개선하고 사용 편의성을 향상시킵니다.

``````